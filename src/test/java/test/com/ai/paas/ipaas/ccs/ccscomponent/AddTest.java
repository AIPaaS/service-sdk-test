package test.com.ai.paas.ipaas.ccs.ccscomponent;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import test.com.ai.paas.ipaas.ccs.ccscomponent.base.CCSComponent;
import test.com.ai.paas.ipaas.ccs.inner.util.ConfigSDKUtil;
import com.ai.paas.ipaas.ccs.constants.AddMode;
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

public class AddTest extends CCSComponent {
	private ICCSComponent iCCSComponent = null;
	private String path = "/test";
	private String stringValue = "test value";
	private byte[] byteValue = new byte[] { 110, 10, 1, 20, 30 };

	@Before
	public void setUp() throws Exception {
		ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
        client.createNode(ConfigSDKUtil.appendUserWritablePathPath(userName), ConfigSDKUtil.createWritableACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "", CreateMode.PERSISTENT);
        assertTrue(client.exists(ConfigSDKUtil.appendUserWritablePathPath(userName)));
		iCCSComponent = super.getClient();
	}

	/***
	 * 正常情况测试 两个参数，value为String类型
	 * writablePath
	 * @throws ConfigException
	 */
	@Test
	public void addPath() throws ConfigException {
		iCCSComponent.add(path, stringValue);
		assertTrue(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
		assertEquals(stringValue,
				iCCSComponent.get(path, ConfigPathMode.WRITABLE));
	}
	
	/***
	 * 正常情况测试 两个参数，value为byte[]类型
	 * writablePath
	 * @throws ConfigException
	 */
	@Test
	public void addByteValue() throws ConfigException {
		iCCSComponent.add(path, byteValue);
		assertTrue(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
		assertArrayEquals(byteValue,
				iCCSComponent.readBytes(path, ConfigPathMode.WRITABLE));
	}
	
	/***
	 * 正常情况测试 三个参数，临时类型
	 * writablePath
	 * @throws ConfigException
	 */
	@Test
	public void addByModeEphemeral() throws ConfigException {
		iCCSComponent.add(path, byteValue,AddMode.EPHEMERAL);
	}
	
	/***
	 * 正常情况测试 三个参数，临时类型序列化
	 * writablePath
	 * @throws ConfigException
	 */
	@Test
	public void addByModeEphemeralSequential() throws ConfigException {
		iCCSComponent.add(path, byteValue,AddMode.EPHEMERAL_SEQUENTIAL);
	}
	
	/***
	 * 正常情况测试 三个参数，持久类型
	 * writablePath
	 * @throws ConfigException
	 */
	@Test
	public void addByModePersistent() throws ConfigException {
		iCCSComponent.add(path, byteValue,AddMode.PERSISTENT);
	}
	
	/***
	 * 正常情况测试 三个参数，持久类型序列化
	 * writablePath
	 * @throws ConfigException
	 */
	@Test
	public void addByModePersistentSequential() throws ConfigException {
		iCCSComponent.add(path, byteValue,AddMode.PERSISTENT_SEQUENTIAL);
	}
	
	/***
	 * path为null测试
	 * 
	 * @throws ConfigException
	 */
	@Test(expected = ConfigException.class)
	public void addPathNull() throws ConfigException {
		iCCSComponent.add(null, stringValue);
	}
	
	/***
	 * value为String类型null测试
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void addValueStringNull() throws ConfigException {
		String stringNull = null;
		iCCSComponent.add(path, stringNull);
		assertTrue(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
		assertArrayEquals(null,
				iCCSComponent.readBytes(path, ConfigPathMode.WRITABLE));
	}
	
	/***
	 * value为byte[]类型null测试
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void addValueByteNull() throws ConfigException {
		byte[] byteNull = null;
		iCCSComponent.add(path, byteNull);
		assertTrue(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
		assertArrayEquals(null,
				iCCSComponent.readBytes(path, ConfigPathMode.WRITABLE));
	}

	/***
	 * 空对象
	 * Path为空
	 * @throws ConfigException
	 */
	@Test(expected = ConfigException.class)
	public void addPathBlank() throws ConfigException {
		iCCSComponent.add("", stringValue);
	}

	/***
	 * 空对象
	 * value为String类型空
	 * @throws ConfigException
	 */
	@Test
	public void addStringValueBlank() throws ConfigException {
		iCCSComponent.add(path, "");
		assertTrue(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
//		assertEquals(null,
//				iCCSComponent.get(path, ConfigPathMode.WRITABLE));
	}
	 
	/***
	 * 空对象
	 * value为byte类型空
	 * @throws ConfigException
	 */
	@Test
	public void addByteValueBlank() throws ConfigException {
		iCCSComponent.add(path, "".getBytes());
		assertTrue(iCCSComponent.exists(path, ConfigPathMode.WRITABLE));
//		assertEquals("".getBytes(),
//				iCCSComponent.readBytes(path, ConfigPathMode.WRITABLE));
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
