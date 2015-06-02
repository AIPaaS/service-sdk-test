package test.com.ai.paas.ipaas.ccs.configclient;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import test.com.ai.paas.ipaas.ccs.configclient.base.ConfigClient;
import test.com.ai.paas.ipaas.ccs.inner.util.ConfigSDKUtil;

import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigCenterConstants;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.ccs.zookeeper.ZKClient;
import com.ai.paas.ipaas.ccs.zookeeper.impl.ZKPoolFactory;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddTest extends ConfigClient {
	private IConfigClient configClient = null;
	private String path = "/test";
	private String stringValue = "test value";
	private byte[] byteValue = new byte[] { 110, 10, 1, 20, 30 };

	@Before
	public void setUp() throws Exception {
		ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
        client.createNode(ConfigSDKUtil.appendUserWritablePathPath(userName), ConfigSDKUtil.createWritableACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "", CreateMode.PERSISTENT);
        assertTrue(client.exists(ConfigSDKUtil.appendUserWritablePathPath(userName)));
		configClient = super.getClient();
	}

	/***
	 * 正常情况测试 两个参数，value为String类型
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void add() throws ConfigException {
		configClient.add(path, stringValue);
		assertTrue(configClient.exists(path));
		assertEquals(stringValue,configClient.get(stringValue));
	}

	/***
	 * 正常情况测试 两个参数，value为byte[]类型
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void addValueByte() throws ConfigException {
		configClient.add(path, byteValue);
		assertTrue(configClient.exists(path));
		assertArrayEquals(byteValue,configClient.readBytes(path));
	}

	/***
	 * null测试
	 * 
	 * @throws ConfigException
	 */
	@Test(expected = ConfigException.class)
	public void addPathNull() throws ConfigException {
		configClient.add(null, stringValue);
	}

	/***
	 * 空对象
	 * 
	 * @throws ConfigException
	 */
	@Test(expected = ConfigException.class)
	public void addPathBlank() throws ConfigException {
		configClient.add("", stringValue);
		configClient.add("", byteValue);
	}

	/***
	 * 空对象
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void addValueBlank() throws ConfigException {
		configClient.add(path, "");
		assertTrue(configClient.exists(path));
		assertEquals(stringValue,configClient.get(stringValue));
	}
	
	/***
	 * 清楚增加的节点记录
	 * 
	 * @throws Exception
	 */
	 @After
     public void clearData() throws Exception {
		 ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName).addAuth("digest",
	                userName + ":" + String.valueOf(userPwd));
	     client.deleteNode(ConfigCenterConstants.UserNodePrefix.FOR_PAAS_PLATFORM_PREFIX + ConfigCenterConstants.SEPARATOR + userName);
	     assertFalse(client.exists(ConfigCenterConstants.UserNodePrefix.FOR_PAAS_PLATFORM_PREFIX + ConfigCenterConstants.SEPARATOR + userName));
     }

}
