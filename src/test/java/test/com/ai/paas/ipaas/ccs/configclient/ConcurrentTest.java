package test.com.ai.paas.ipaas.ccs.configclient;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ai.paas.ipaas.ccs.ConfigFactory;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class ConcurrentTest {
	
	private static int thread_num;
	private static int times;
	private static String type;//add ，delete，modify，get
	private static final String URL = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private static final String USER_NAME = "371308601@qq.com";
	private static final String PASSWORD = "123456";
	private static final String SERVICE_ID = "CCS001";
	private static AuthDescriptor ad = null;
	private static IConfigClient configClient;
	
	static {
		ad = new AuthDescriptor(URL, USER_NAME, PASSWORD, SERVICE_ID);
		try {
			configClient = ConfigFactory.getConfigClient(ad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		thread_num = Integer.parseInt(args[0]);
		times = Integer.parseInt(args[1]);
		type = args[2];
		long t = System.currentTimeMillis();
		withPool(thread_num,times,type);
		long elapsed = System.currentTimeMillis() - t;
		System.out.println(elapsed+"---------"+((1000 * times * thread_num) / elapsed) + " ops");
	}
	
	private static void withPool(int thread_num,final int times,final String type) throws Exception {
		List<Thread> tds = new ArrayList<Thread>();
		for (int i = 0; i < thread_num; i++) {
			final int k = i;
			final AtomicInteger ind = new AtomicInteger();
			Thread hj = new Thread(new Runnable() {
				public void run() {
					for (int j = 0; (j = ind.getAndIncrement()) < times;) {
						final String key = "/"+k+"----"+j;
						try {
							switch(type){
								case "add" : configClient.add(key,"");break;
								case "delete" : configClient.remove(key);;break;
								case "modify" : configClient.modify(key,"123");break;
								case "get" : configClient.get(key);break;
								default : System.out.println("error");
							}
						} catch (ConfigException e) {
							e.printStackTrace();
						}
					}
				}
			});
			tds.add(hj);
			hj.start();
		}
		for (Thread t : tds)
			t.join();
	}
}
