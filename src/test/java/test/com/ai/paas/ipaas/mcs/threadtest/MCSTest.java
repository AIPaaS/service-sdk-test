package test.com.ai.paas.ipaas.mcs.threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ai.paas.ipaas.mcs.CacheFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class MCSTest {
	private static final String URL = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private static final String USER_NAME = "yimingc777@126.com";
	private static final String PASSWORD = "1234567";
	private static final String SERVICE_ID = "MCS003";
	private static AuthDescriptor ad = null;
	private static ICacheClient cacheClient;
	private static int thread_num;
	private static int times;
	private static String fun = "";

	static {
		ad = new AuthDescriptor(URL, USER_NAME, PASSWORD, SERVICE_ID);
		try {
			cacheClient = CacheFactory.getClient(ad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		if(args.length == 2) {
			thread_num = Integer.parseInt(args[0]);
			times = Integer.parseInt(args[1]);
		}else if(args.length ==3){
			thread_num = Integer.parseInt(args[0]);
			times = Integer.parseInt(args[1]);
			fun = args[2];
		}else {
			System.out.println("param error...");
			return;
		}
		List<Thread> threadPool = new ArrayList<Thread>();
		if ("set".equals(fun.toLowerCase())) {
			long t = System.currentTimeMillis();
			withPoolSet(thread_num, times);
			long elapsed = System.currentTimeMillis() - t;
			System.out.println(elapsed + "---------"
					+ ((1000 * times) / elapsed) + " ops");
		} else {
			long t = System.currentTimeMillis();
			withPool(thread_num, times);
			long elapsed = System.currentTimeMillis() - t;
			System.out.println(elapsed + "---------"
					+ ((1000 * 2 * times) / elapsed) + " ops");
		}
	}


	private static void withPool(int thread_num, final int times)
			throws Exception {
		List<Thread> tds = new ArrayList<Thread>();
		for (int i = 0; i < thread_num; i++) {
			final int k = i;
			final AtomicInteger ind = new AtomicInteger();
			Thread hj = new Thread(new Runnable() {
				public void run() {
					for (int j = 0; (j = ind.getAndIncrement()) < times;) {
						final String key = k + "foo" + j;
						cacheClient.set("234", "234");
						cacheClient.get("234");
					}
				}
			});
			tds.add(hj);
			hj.start();
		}
		for (Thread t : tds)
			t.join();
	}

	private static void withPoolSet(int thread_num, final int times)
			throws Exception {
		List<Thread> tds = new ArrayList<Thread>();
		for (int i = 0; i < thread_num; i++) {
			final int k = i;
			final AtomicInteger ind = new AtomicInteger();
			Thread hj = new Thread(new Runnable() {
				public void run() {
					for (int j = 0; (j = ind.getAndIncrement()) < times;) {
						final String key = k + "foo" + j;
						cacheClient.set(key, key);
						// cacheClient.get(key);
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
