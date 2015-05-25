package test.com.ai.paas.ipaas.dbs.sequencetoolclient.base;

import com.ai.paas.ipaas.dbs.sequence.tool.ISequenceToolClient;
import com.ai.paas.ipaas.dbs.sequence.tool.SequenceToolFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class SequenceToolClient {
	private static final String URL = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private static final String USER_NAME = "zh_ka@163.com";
	private static final String PASSWORD = "123456";
	private static final String SERVICE_ID = "DBS001";
	private AuthDescriptor ad = null;
	private ISequenceToolClient iSequenceToolClient = null;

	public ISequenceToolClient getClient() throws Exception {
		ad =  new AuthDescriptor(URL, USER_NAME, PASSWORD, SERVICE_ID);
		iSequenceToolClient = SequenceToolFactory.getClient(ad);
		return iSequenceToolClient;
	}
}
