package test.com.ai.paas.ipaas.dbs.distribute.distributedDataSource;

import org.junit.Test;

import com.ai.paas.ipaas.dbs.distribute.DistributedDataSource;

public class DistributedDataSourceTest {
	
	private static final String URL = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private static final String USER_NAME = "zh_ka@163.com";
	private static final String PASSWORD = "123456";
	private static final String SERVICE_ID = "DBS001";
	
	private  DistributedDataSource ds;
	
	@Test
	public void setup() throws Exception {
		
		ds = new DistributedDataSource(USER_NAME,PASSWORD,SERVICE_ID,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlNull() throws Exception {
		ds = new DistributedDataSource(USER_NAME,PASSWORD,SERVICE_ID,null);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernameNull() throws Exception {
		ds = new DistributedDataSource(null,PASSWORD,SERVICE_ID,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdNull() throws Exception {
		ds = new DistributedDataSource(USER_NAME,null,SERVICE_ID,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupservIdNull() throws Exception {
		ds = new DistributedDataSource(USER_NAME, PASSWORD, null, URL);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlUsernameNull() throws Exception {
		ds = new DistributedDataSource(null,PASSWORD,SERVICE_ID,null);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernamePwdNull() throws Exception {
		ds = new DistributedDataSource(null,null,SERVICE_ID,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdServIdNull() throws Exception {
		ds = new DistributedDataSource(USER_NAME,null,null,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlServIdNull() throws Exception {
		ds = new DistributedDataSource(USER_NAME,PASSWORD,null,null);
	}
	
	@Test(expected = Exception.class)
	public void setupServIdNotNull() throws Exception {
		ds = new DistributedDataSource(null,null,SERVICE_ID,null);
	}
	
	@Test(expected = Exception.class)
	public void setupUsernameNotNull() throws Exception {
		ds = new DistributedDataSource(USER_NAME,null,null,null);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdNotNull() throws Exception {
		ds = new DistributedDataSource(null,PASSWORD,null,null);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlNotNull() throws Exception {
		ds = new DistributedDataSource(null,null,null,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupAllNull() throws Exception {
		ds = new DistributedDataSource(null, null, null, null);
	}

	@Test(expected = Exception.class)
	public void setupUrlBlank() throws Exception {
		ds = new DistributedDataSource(USER_NAME, PASSWORD, SERVICE_ID, "");
	}
	
	@Test(expected = Exception.class)
	public void setupUsernameBlank() throws Exception {
		ds = new DistributedDataSource("",PASSWORD,SERVICE_ID,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdBlank() throws Exception {
		ds = new DistributedDataSource(USER_NAME,"",SERVICE_ID,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupservIdBlank() throws Exception {
		ds = new DistributedDataSource(PASSWORD,"","",URL);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlUsernameBlank() throws Exception {
		ds = new DistributedDataSource("",PASSWORD,SERVICE_ID,"");
	}
	
	@Test(expected = Exception.class)
	public void setupUsernamePwdBlank() throws Exception {
		ds = new DistributedDataSource("","",SERVICE_ID,URL);
	}
	
	@Test(expected = Exception.class)
	public void setupPwdServIdBlank() throws Exception {
		ds = new DistributedDataSource(USER_NAME,"","",URL);
	}
	
	@Test(expected = Exception.class)
	public void setupUrlServIdBlank() throws Exception {
		ds = new DistributedDataSource(USER_NAME,PASSWORD,"","");
	}
	
	@Test(expected = Exception.class)
	public void setupServIdNotBlank() throws Exception {
		ds = new DistributedDataSource("","",SERVICE_ID,"");
	}
	
	@Test(expected = Exception.class)
	public void setupUsernameNotBlank() throws Exception {
		ds = new DistributedDataSource(USER_NAME, "", "", "");
	}
	
	@Test(expected = Exception.class)
	public void setupPwdNotBlank() throws Exception {
		ds = new DistributedDataSource("", PASSWORD, "", "");
	}
	
	@Test(expected = Exception.class)
	public void setupUrlNotBlank() throws Exception {
		ds = new DistributedDataSource("", "", "", URL);
	}
	
	@Test(expected = Exception.class)
	public void setupAllBlank() throws Exception {
		ds = new DistributedDataSource("", "", "", "");
	}

}
