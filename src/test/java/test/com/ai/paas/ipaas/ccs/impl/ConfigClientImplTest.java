package test.com.ai.paas.ipaas.ccs.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.com.ai.paas.ipaas.ccs.impl.util.ConfigSDKUtil;

import com.ai.paas.ipaas.ccs.ConfigFactory;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigCenterConstants;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.ccs.zookeeper.ZKClient;
import com.ai.paas.ipaas.ccs.zookeeper.impl.ZKPoolFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.paas.ipaas.util.CiperUtil;
import com.ai.paas.ipaas.util.UUIDTool;

/**
 * Created by astraea on 2015/4/29.
 */
public class ConfigClientImplTest {
    private final String configAddr = "127.0.0.1:2181";
    private final String modifyValueC = "test Path C";

    private String userName = UUIDTool.genId32();
    private int userPwd = UUIDTool.genShortId();

    private String adminName = "admin";
    private String adminPwd = "ec4c9e0e78f76a69";

    private String parentPath = "/test";
    private String testPathA = parentPath + "/testA";
    private String testPathB = parentPath + "/testB";
    private String serviceId = UUIDTool.genId32();

    private String testPathAValue = "test a ttttAAAA";
    private byte[] testPathBValue = new byte[]{1, 20, 30, 50};

    @Before
    public void setUp() throws Exception {
        ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
        client.createNode(ConfigSDKUtil.appendUserNodePath(userName, serviceId), ConfigSDKUtil.createWritableACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "test a tttt", CreateMode.PERSISTENT);
        assertTrue(client.exists(ConfigSDKUtil.appendUserNodePath(userName, serviceId)));
        testAdd();
    }

    @Test(expected = ConfigException.class)
    public void testmodifyRooltPath() throws Exception {
        IConfigClient client = getiConfigClient();
        client.modify("", "test Root");
    }

    @After
    public void tearDown() throws Exception {
        ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
        client.deleteNode(ConfigSDKUtil.appendUserNodePath(userName, serviceId));
        assertFalse(client.exists(ConfigSDKUtil.appendUserNodePath(userName, serviceId)));
    }

    public void testAdd() throws Exception {
        IConfigClient client = getiConfigClient();
        client.add(testPathA, testPathAValue);
        assertTrue(client.exists(testPathA));
        assertEquals(client.get(testPathA), testPathAValue);

        client.add(testPathB, testPathBValue);
        assertTrue(client.exists(testPathB));
        assertArrayEquals(client.readBytes(testPathB), testPathBValue);
    }


    @Test(expected = ConfigException.class)
    public void testGetNoExistPath() throws Exception {
        IConfigClient client = getiConfigClient();
        client.get(testPathB + 1);
    }

    @Test(expected = ConfigException.class)
    public void testReadBytesNoExist() throws Exception {
        IConfigClient client = getiConfigClient();
        client.readBytes(testPathB + 1);
    }

    @Test
    public void testModify() throws Exception {
        IConfigClient client = getiConfigClient();
        client.modify(testPathA, modifyValueC);
        assertTrue(client.exists(testPathA));

        assertEquals(client.get(testPathA), modifyValueC);
    }

    @Test(expected = ConfigException.class)
    public void testModifyNoExistPath() throws Exception {
        IConfigClient client = getiConfigClient();
        client.modify(testPathA + 1, modifyValueC);
    }

    @Test
    public void testRemove() throws Exception {
        IConfigClient client = getiConfigClient();
        client.remove(testPathA);
        assertFalse(client.exists(testPathA));
    }

    @Test
    public void testListSubPath() throws Exception {
        IConfigClient client = getiConfigClient();
        List<String> subPaths = client.listSubPath(parentPath);
        assertEquals(2, subPaths.size());
    }

    @Test
    public void testNoExistListSubPath() throws Exception {
        IConfigClient client = getiConfigClient();
        List<String> subPaths = client.listSubPath(testPathA);
        assertNotNull(subPaths);
        assertEquals(0, subPaths.size());
    }

    @Test(expected = ConfigException.class)
    public void testNoExistPathListSubPath() throws Exception {
        IConfigClient client = getiConfigClient();
        List<String> subPaths = client.listSubPath(testPathA + 1);
    }

    private IConfigClient getiConfigClient() throws Exception {
        AuthDescriptor descriptor = new AuthDescriptor();
        descriptor.setServiceId(serviceId);
        descriptor.setUserName(userName);
        descriptor.setPassword(CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));

        return ConfigFactory.getConfigClient(descriptor);
    }
}