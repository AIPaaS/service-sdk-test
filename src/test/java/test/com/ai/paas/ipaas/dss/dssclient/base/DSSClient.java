package test.com.ai.paas.ipaas.dss.dssclient.base;

import com.ai.paas.ipaas.dss.DSSFactory;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class DSSClient {
	
	private static final String URL = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private static final String USER_NAME = "zhanglei11@asiainfo.com";
	private static final String PASSWORD = "1234567";
	private static final String SERVICE_ID = "DSS001";
	private AuthDescriptor ad = null;

	public IDSSClient getClient() throws Exception {
		ad = new AuthDescriptor(URL, USER_NAME, PASSWORD, SERVICE_ID);
		return DSSFactory.getClient(ad);
	}
}
