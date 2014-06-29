package test.com.ai.paas.ipaas.mds.messageconsumer;

import org.junit.Before;
import org.junit.Test;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import com.ai.paas.ipaas.mds.MsgConsumerFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class MsgConsumerTest1 {
	private static IMessageConsumer msgConsumer = null;

	 @Before
	public void setUp() throws Exception {
		String srvId = "MDS001";
		String authAddr = "http://10.1.228.198:14821/iPaas-Auth/service/check";
		AuthDescriptor ad = new AuthDescriptor(authAddr,"809048114@qq.com","586913",srvId);
		String topic ="D3C1848F571B461CA39278F6166507B9_MDS001_614448406";  
		IMsgProcessorHandler msgProcessorHandler = new MsgProcessorHandlerImpl1();
		msgConsumer = MsgConsumerFactory.getClient(ad, topic,
				msgProcessorHandler);  
	}

	@Test  
	public void consumeMsg() {
		msgConsumer.start();
		while (true) {
			try {    
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}   
		}  
	}  
	@Test
	public void consumeMsg1(){  //test
		 msgConsumer.stop();
	}
}
