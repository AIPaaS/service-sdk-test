package test.com.ai.paas.ipaas.ccs.configclient.base;

import com.ai.paas.ipaas.ccs.ConfigFactory;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigCenterConstants;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.paas.ipaas.util.CiperUtil;

public class ConfigClient {
	protected final String configAddr = "10.1.228.198:39182";
	protected String userName = "371308601@qq.com";
	protected String userPwd = "123456";
	protected String adminName = "admin";
	protected String adminPwd = CiperUtil.decrypt(ConfigCenterConstants.operators, "ec4c9e0e78f76a69");
	private final String AUTH_ADDR = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private AuthDescriptor ad = null;
	private IConfigClient iConfigClient = null;

	public IConfigClient getClient() throws Exception {
		ad = new AuthDescriptor(AUTH_ADDR, "371308601@qq.com", "123456",
				"CCS002"); 
		iConfigClient = ConfigFactory.getConfigClient(ad);
		return iConfigClient;
	}
}
