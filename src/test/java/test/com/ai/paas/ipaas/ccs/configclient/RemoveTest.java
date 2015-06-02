package test.com.ai.paas.ipaas.ccs.configclient;

import static org.junit.Assert.assertTrue;
import test.com.ai.paas.ipaas.ccs.configclient.base.ConfigClient;
import test.com.ai.paas.ipaas.ccs.inner.util.ConfigSDKUtil;

import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.ccs.zookeeper.ZKClient;
import com.ai.paas.ipaas.ccs.zookeeper.impl.ZKPoolFactory;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

public class RemoveTest extends ConfigClient {
	private IConfigClient configClient = null;
	private String path = "/test";

	@Before
	public void setUp() throws Exception {
		ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
        client.createNode(ConfigSDKUtil.appendUserWritablePathPath(userName), ConfigSDKUtil.createWritableACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "", CreateMode.PERSISTENT);
        assertTrue(client.exists(ConfigSDKUtil.appendUserWritablePathPath(userName)));
		configClient = super.getClient();
		configClient.add(path,"test remove function");
	}

	/***
	 * 正常情况测试
	 * 
	 * @throws ConfigException
	 */
	@Test
	public void remove() throws ConfigException {
		configClient.remove(path);
	}

}
