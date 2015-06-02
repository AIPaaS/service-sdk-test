package test.com.ai.paas.ipaas.ccs.configclient;

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

public class ModifyTest extends ConfigClient {
	private IConfigClient configClient = null;
	private String path = "/test/readonly";
	private String strValue = "test string value";
	private byte[] byteValue = new byte[] { 110, 10, 1, 20, 30 };

	@Before
	public void setUp() throws Exception {
		ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
        client.createNode(ConfigSDKUtil.appendUserWritablePathPath(userName), ConfigSDKUtil.createWritableACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "", CreateMode.PERSISTENT);
        assertTrue(client.exists(ConfigSDKUtil.appendUserWritablePathPath(userName)));
		configClient = super.getClient();
		configClient.add(path,"test modify function");
	}

	/***
	 * 正常情况测试 StringValue
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void modify() throws ConfigException {
		configClient.modify(path, strValue);
	}

	/***
	 * 正常情况测试 ByteValue
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void modifyByteValue() throws ConfigException {
		configClient.modify(path, byteValue);
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
