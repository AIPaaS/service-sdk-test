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
		//List<Thread> threadPool = new ArrayList<Thread>();
		long t = System.currentTimeMillis();
		withPool(thread_num,times);
		long elapsed = System.currentTimeMillis() - t;
		System.out.println(elapsed+"---------"+((1000 * times) / elapsed) + " ops");
	}
	
	private static void withPool(int thread_num,final int times) throws Exception {
		List<Thread> tds = new ArrayList<Thread>();
		for (int i = 0; i < thread_num; i++) {
			final int k = i;
			final AtomicInteger ind = new AtomicInteger();
			Thread hj = new Thread(new Runnable() {
				public void run() {
					for (int j = 0; (j = ind.getAndIncrement()) < times;) {
						final String key = "/"+k+"----"+j;
						try {
							configClient.get(key);
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



//	public static void main(String[] args) throws Exception {
//		
//		String userName = "371308601@qq.com";
//		
//		String userPwd = "123456";
//		
//		String AUTH_ADDR = "http://10.1.228.198:14821/iPaas-Auth/service/check";
//		
//		AuthDescriptor ad = new AuthDescriptor(AUTH_ADDR, userName, userPwd, "CCS001"); 
//		
//		final IConfigClient iConfigClient = ConfigFactory.getConfigClient(ad);
//
//		int thread_num = 1000;
//		runTestCase(iConfigClient,99);
//
//		List<Thread> threadPool = new ArrayList<Thread>();
//		for (int index = 0; index < thread_num; index++) {
//			
//			final int NO = index + 100;
//			Runnable run = new Runnable() {
//				public void run() {
//					try {
//						runTestCase(iConfigClient,NO);
//						// 业务逻辑
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			};
//			Thread testThread = new Thread(run);
//			threadPool.add(testThread);
//		}
//		
//		thread_num = Integer.parseInt(args[0]);
//		times = Integer.parseInt(args[1]);
//		List<Thread> threadPool = new ArrayList<Thread>();
//		
//		long t = System.currentTimeMillis();
//		
//		for (Thread testThread : threadPool) {
//			testThread.start();
//		}
//		long elapsed = System.currentTimeMillis() - t;
//		System.out.println(elapsed+"---------"+((1000 * times) / elapsed) + " ops");
//
//		Thread.sleep(1000000L);
//		// runTestCase(ds,101);
//	}

	public static void runTestCase(IConfigClient iConfigClient,int NO)
			throws Exception {
		testAdd(iConfigClient,NO);
	}
	
	public static void testAdd(IConfigClient iConfigClient,int NO) throws ConfigException{
		iConfigClient.add("/abc"+NO, "abcdefghijklmnopqrstuvwxyz");
	}
	
	public static void testDelete(IConfigClient iConfigClient,int NO) throws ConfigException{
		iConfigClient.remove("/abc"+NO);
	}
	
	public static void testGet(IConfigClient iConfigClient,int NO) throws ConfigException{
		iConfigClient.get("/abc"+NO);
	}
	
	public static void testModify(IConfigClient iConfigClient,int NO) throws ConfigException{
		iConfigClient.modify("/abc"+NO, "test modified");
	}
}
