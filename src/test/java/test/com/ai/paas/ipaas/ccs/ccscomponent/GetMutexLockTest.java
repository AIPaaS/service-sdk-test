package test.com.ai.paas.ipaas.ccs.ccscomponent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import test.com.ai.paas.ipaas.ccs.ccscomponent.base.CCSComponent;
import test.com.ai.paas.ipaas.ccs.inner.util.ConfigSDKUtil;

import com.ai.paas.ipaas.ccs.constants.ConfigCenterConstants;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.ccs.inner.ICCSComponent;
import com.ai.paas.ipaas.ccs.zookeeper.ZKClient;
import com.ai.paas.ipaas.ccs.zookeeper.impl.ZKPoolFactory;

import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetMutexLockTest extends CCSComponent {
	private ICCSComponent iCCSComponent = null;
	private String path = "/test";

	@Before
	public void setUp() throws Exception {
		ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
        client.createNode(ConfigSDKUtil.appendUserWritablePathPath(userName), ConfigSDKUtil.createWritableACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "", CreateMode.PERSISTENT);
        assertTrue(client.exists(ConfigSDKUtil.appendUserWritablePathPath(userName)));
		iCCSComponent = super.getClient();
		iCCSComponent.add(path,"test GetMutexLock function");
	}

	/***
	 * 正常情况测试
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void getMutexLock() throws ConfigException {
		iCCSComponent.getMutexLock(path);
	}

	/***
	 * null测试
	 * 
	 * @throws ConfigException
	 */
	@Test(expected = ConfigException.class)
	public void getMutexLockFirstNull() throws ConfigException {
		iCCSComponent.getMutexLock(null);
	}

	/***
	 * 空对象
	 * 
	 * @throws ConfigException
	 */
	@Test(expected = ConfigException.class)
	public void getMutexLockFirstBlank() throws ConfigException {
		iCCSComponent.getMutexLock("");
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
