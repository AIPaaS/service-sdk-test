package sdk.demo.mds;

import java.util.ArrayList;
import java.util.List;

import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.beust.jcommander.JCommander;

public class MessageSendTest {

	private static IMessageSender msgSender = null;

	public static void main(String[] args) {
		CommandArg commd = new CommandArg();
		new JCommander(commd, args);
		AuthDescriptor ad = new AuthDescriptor(commd.getAuthAddr(),
				commd.getAuthUser(), commd.getAuthPasswd(), commd.getSrvId());
		long t = System.currentTimeMillis();
		try {
			withPool(commd.getThreadNum(), commd.getTimes(), ad,
					commd.getTopic());
		} catch (Exception e) {
			e.printStackTrace();
		}
		long elapsed = System.currentTimeMillis() - t;
		System.out
				.println(elapsed
						+ "---------"
						+ ((1000 * 2 * commd.getThreadNum() * commd.getTimes()) / elapsed)
						+ " ops");
	}

	private static void withPool(int thread_num, final int times,
			AuthDescriptor ad, String topic) throws Exception {

		msgSender = MsgSenderFactory.getClient(ad, topic);
		List<Thread> tds = new ArrayList<Thread>();
		for (int i = 0; i < thread_num; i++) {
			Thread hj = new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < times; j++) {
						try {
							msgSender.send("Test Message " + j, j);
						} catch (Exception e) {
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
