package test.com.ai.paas.ipaas.ccs.ccscomponent.concurrent;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.zookeeper.CreateMode;




import test.com.ai.paas.ipaas.ccs.inner.util.ConfigSDKUtil;

import com.ai.paas.ipaas.ccs.constants.ConfigCenterConstants;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.ccs.inner.CCSComponentFactory;
import com.ai.paas.ipaas.ccs.inner.ICCSComponent;
import com.ai.paas.ipaas.ccs.inner.constants.ConfigPathMode;
import com.ai.paas.ipaas.ccs.zookeeper.ZKClient;
import com.ai.paas.ipaas.ccs.zookeeper.impl.ZKPoolFactory;
import com.ai.paas.ipaas.util.CiperUtil;


public class ConcurrentTest {
	
	private static int thread_num;
	private static int times;
	private static String type;//add ，delete，modify，get
    private static String userPwd = "123456";
    private final static String configAddr = "10.125.3.247:29181";
    private static String adminName = "admin";
    private static String adminPwd = "admin";
		
	public static void main(String[] args) throws Exception {
		thread_num = Integer.valueOf(args[0]);
		times = Integer.valueOf(args[1]);
		type = args[2];
//		thread_num = 100;
//		times = 1000;
//		type = "add";
		long t = System.currentTimeMillis();
		withPool(thread_num,times,type);
		long elapsed = (System.currentTimeMillis() - t)==0?1:System.currentTimeMillis() - t;
		System.out.println(elapsed+"---------"+((1000 * times * thread_num) / elapsed) + " ops");
	}
	
	private static void withPool(int thread_num,final int times,final String type) throws Exception {
		List<Thread> tds = new ArrayList<Thread>();
		for (int i = 0; i < thread_num; i++) {
			final int k = i;
			final AtomicInteger ind = new AtomicInteger();
			Thread hj = new Thread(new Runnable() {
				public void run() {
					ICCSComponent configClient;
					try {
						configClient = getICCSComponent(k);
						for (int j = 0; (j = ind.getAndIncrement()) < times;) {
							final String key = "/"+k+"----"+j;
							try {
								switch(type){
									case "add" : configClient.add(key,"123456");break;
									case "delete" : configClient.remove(key);break;
									case "modify" : configClient.modify(key,"123");break;
									case "get" : configClient.get(key,ConfigPathMode.WRITABLE);break;
									default : System.out.println("error");
								}
							} catch (ConfigException e) {
								e.printStackTrace();
							}
						}
					}  catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			tds.add(hj);
			hj.start();
		}
		for (Thread t : tds)
			t.join();
	}

	public static ICCSComponent getICCSComponent(int k) throws ConfigException, Exception{
		String userName = "000000"+k;
		ICCSComponent iCCSComponent = null;
		ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
		if(!client.exists(ConfigSDKUtil.appendUserWritablePathPath(userName))){
			client.createNode(ConfigSDKUtil.appendUserWritablePathPath(userName), ConfigSDKUtil.createWritableACL(userName,
			        String.valueOf(userPwd), adminName, adminPwd), "test value", CreateMode.PERSISTENT);
		}				
		iCCSComponent = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        return iCCSComponent;
	}
}
