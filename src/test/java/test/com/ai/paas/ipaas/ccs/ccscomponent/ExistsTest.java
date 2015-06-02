package test.com.ai.paas.ipaas.ccs.ccscomponent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import test.com.ai.paas.ipaas.ccs.ccscomponent.base.CCSComponent;
import test.com.ai.paas.ipaas.ccs.inner.util.ConfigSDKUtil;
import com.ai.paas.ipaas.ccs.constants.ConfigCenterConstants;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.ccs.inner.ICCSComponent;
import com.ai.paas.ipaas.ccs.inner.constants.ConfigPathMode;
import com.ai.paas.ipaas.ccs.zookeeper.ZKClient;
import com.ai.paas.ipaas.ccs.zookeeper.impl.ZKPoolFactory;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExistsTest extends CCSComponent {
	private String path = "/test";
	private ICCSComponent iCCSComponent = null;

	@Before
	public void setUp() throws Exception {
		ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
        client.createNode(ConfigSDKUtil.appendUserWritablePathPath(userName), ConfigSDKUtil.createWritableACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "", CreateMode.PERSISTENT);
        assertTrue(client.exists(ConfigSDKUtil.appendUserWritablePathPath(userName)));
		iCCSComponent = super.getClient();
		iCCSComponent.add(path,"test exist function");
	}

	/***
	 * 正常情况测试,不指定ConfigPathMode,默认只读路径
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void exists() throws ConfigException {
		iCCSComponent.exists(path);
		assertTrue(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
	}
	
	/***
	 * 正常情况测试，指定ConfigPathMode
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void existsWithPathMode() throws ConfigException {
		iCCSComponent.exists(path,ConfigPathMode.WRITABLE);
		assertTrue(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
	}
	
	/***
	 * 节点不存在
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void existsNoPath() throws ConfigException {
		iCCSComponent.exists(path+1);
		assertFalse(iCCSComponent.exists(path));
	}

	/***
	 * 节点不存在
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void existsNoPathWithPathMode() throws ConfigException {
		iCCSComponent.exists(path+1,ConfigPathMode.WRITABLE);
		assertFalse(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
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
