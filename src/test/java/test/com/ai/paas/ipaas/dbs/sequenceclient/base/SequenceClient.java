package test.com.ai.paas.ipaas.dbs.sequenceclient.base;

import com.ai.paas.ipaas.dbs.sequence.ISequenceClient;
import com.ai.paas.ipaas.dbs.sequence.SequenceFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class SequenceClient {
	private static final String URL = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private static final String USER_NAME = "zh_ka@163.com";
	private static final String PASSWORD = "123456";
	private static final String SERVICE_ID = "DBS001";
	private AuthDescriptor ad = null;
	private ISequenceClient iSequenceClient = null;

	public ISequenceClient getClient() throws Exception {
		ad = new AuthDescriptor(URL, USER_NAME, PASSWORD, SERVICE_ID);
		iSequenceClient = SequenceFactory.getClient(ad);
		return iSequenceClient;
	}
}
