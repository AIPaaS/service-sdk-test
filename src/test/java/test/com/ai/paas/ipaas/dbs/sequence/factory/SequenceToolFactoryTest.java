package test.com.ai.paas.ipaas.dbs.sequence.factory;

import org.junit.Test;

import com.ai.paas.ipaas.dbs.sequence.tool.ISequenceToolClient;
import com.ai.paas.ipaas.dbs.sequence.tool.SequenceToolFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class SequenceToolFactoryTest {
	
	private static AuthDescriptor ad = null;
	private static ISequenceToolClient sc = null;
	private static final String URL = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private static final String USER_NAME = "zh_ka@163.com";
	private static final String PASSWORD = "123456";
	private static final String SERVICE_ID = "DBS001";
	
	@Test
	public void setup() throws Exception {
		ad =  new AuthDescriptor(URL, USER_NAME, PASSWORD, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlNull() throws Exception {
		ad =  new AuthDescriptor(null, USER_NAME, PASSWORD, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernameNull() throws Exception {
		ad =  new AuthDescriptor(URL, null, PASSWORD, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdNull() throws Exception {
		ad =  new AuthDescriptor(URL, USER_NAME, null, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupservIdNull() throws Exception {
		ad =  new AuthDescriptor(URL, USER_NAME, PASSWORD, null);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlUsernameNull() throws Exception {
		ad =  new AuthDescriptor(null, null, PASSWORD, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernamePwdNull() throws Exception {
		ad =  new AuthDescriptor(URL, null, null, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdServIdNull() throws Exception {
		ad =  new AuthDescriptor(URL, USER_NAME, null, null);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlServIdNull() throws Exception {
		ad =  new AuthDescriptor(null, USER_NAME, PASSWORD, null);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupServIdNotNull() throws Exception {
		ad =  new AuthDescriptor(null, null, null, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernameNotNull() throws Exception {
		ad =  new AuthDescriptor(null, USER_NAME, null, null);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdNotNull() throws Exception {
		ad =  new AuthDescriptor(null, null, PASSWORD, null);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlNotNull() throws Exception {
		ad =  new AuthDescriptor(URL, null, null, null);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupAllNull() throws Exception {
		ad =  new AuthDescriptor(null, null, null, null);
		sc = SequenceToolFactory.getClient(ad);
	}

	@Test(expected = Exception.class)
	public void setupUrlBlank() throws Exception {
		ad =  new AuthDescriptor("", USER_NAME, PASSWORD, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernameBlank() throws Exception {
		ad =  new AuthDescriptor(URL, "", PASSWORD, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdBlank() throws Exception {
		ad =  new AuthDescriptor(URL, USER_NAME, "", SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupservIdBlank() throws Exception {
		ad =  new AuthDescriptor(URL, USER_NAME, PASSWORD, "");
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlUsernameBlank() throws Exception {
		ad =  new AuthDescriptor("", "", PASSWORD, SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernamePwdBlank() throws Exception {
		ad =  new AuthDescriptor(URL, "", "", SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdServIdBlank() throws Exception {
		ad =  new AuthDescriptor(URL, USER_NAME, "", "");
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlServIdBlank() throws Exception {
		ad =  new AuthDescriptor("", USER_NAME, PASSWORD, "");
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupServIdNotBlank() throws Exception {
		ad =  new AuthDescriptor("", "", "", SERVICE_ID);
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernameNotBlank() throws Exception {
		ad =  new AuthDescriptor("", USER_NAME, "", "");
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdNotBlank() throws Exception {
		ad =  new AuthDescriptor("", "", PASSWORD, "");
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlNotBlank() throws Exception {
		ad =  new AuthDescriptor(URL, "", "", "");
		sc = SequenceToolFactory.getClient(ad);
	}
	
	@Test(expected = Exception.class)
	public void setupAllBlank() throws Exception {
		ad =  new AuthDescriptor("", "", "", "");
		sc = SequenceToolFactory.getClient(ad);
	}

}
