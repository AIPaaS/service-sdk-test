package test.com.ai.paas.ipaas.uac.uacfactory;

import org.junit.Test;

import com.ai.paas.ipaas.uac.service.IUserClient;
import com.ai.paas.ipaas.uac.service.UserClientFactory;

public class UserFactoryTest {
	private IUserClient userClient = null;
	@Test
	public void getClient() throws Exception {
		userClient = UserClientFactory.getUserClient();
	}
	
}
