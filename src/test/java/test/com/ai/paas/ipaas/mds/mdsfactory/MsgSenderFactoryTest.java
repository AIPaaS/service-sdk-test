package test.com.ai.paas.ipaas.mds.mdsfactory;

import org.junit.Test;

import com.ai.paas.ipaas.mcs.CacheFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class MsgSenderFactoryTest {
	private final String AUTH_ADDR = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private AuthDescriptor ad = null;
	private IMessageSender iMessageSender = null;
	
	@Test
	public void getClient() throws Exception {
		ad = new AuthDescriptor(AUTH_ADDR, "393170232@qq.com", "123456",
				"MDS002");
		iMessageSender = MsgSenderFactory.getClient(ad, "B9178FB878834E7BA8CD02FB981C7F4D_MDS002_553246307");
	}
	@Test(expected = IllegalArgumentException.class)
	public void authNullTest() throws Exception{
		iMessageSender = MsgSenderFactory.getClient(null,"B9178FB878834E7BA8CD02FB981C7F4D_MDS002_553246307");
	}
	@Test(expected = IllegalArgumentException.class)
	public void authTest() throws Exception{
		iMessageSender = MsgSenderFactory.getClient(new AuthDescriptor(),"B9178FB878834E7BA8CD02FB981C7F4D_MDS002_553246307");
	}
	@Test(expected = IllegalArgumentException.class)
	public void topicnNullTest() throws Exception{
		iMessageSender = MsgSenderFactory.getClient(ad,null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void topicnTest() throws Exception{
		iMessageSender = MsgSenderFactory.getClient(ad,"");
	}
	
}
