package test.com.ai.paas.ipaas.uac.userclient.base;

import com.ai.paas.ipaas.uac.service.IUserClient;
import com.ai.paas.ipaas.uac.service.UserClientFactory;

public class UserClient {

	public IUserClient getClient() throws Exception {
		return UserClientFactory.getUserClient();
	}
}
