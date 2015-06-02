package test.com.ai.paas.ipaas.ccs.inner;

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

import test.com.ai.paas.ipaas.ccs.inner.util.ConfigSDKUtil;

import com.ai.paas.ipaas.ccs.constants.ConfigCenterConstants;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.ccs.inner.CCSComponentFactory;
import com.ai.paas.ipaas.ccs.inner.ICCSComponent;
import com.ai.paas.ipaas.ccs.inner.constants.ConfigPathMode;
import com.ai.paas.ipaas.ccs.zookeeper.ConfigWatcher;
import com.ai.paas.ipaas.ccs.zookeeper.ConfigWatcherEvent;
import com.ai.paas.ipaas.ccs.zookeeper.ZKClient;
import com.ai.paas.ipaas.ccs.zookeeper.impl.ZKPoolFactory;
import com.ai.paas.ipaas.util.CiperUtil;
import com.ai.paas.ipaas.util.UUIDTool;

public class ICCSComponentTest {

    private final String configAddr = "127.0.0.1:2181";
    private final String writableValue = "test value";

    private String userName = UUIDTool.genId32();
    private int userPwd = UUIDTool.genShortId();

    private String adminName = "admin";
    private String adminPwd = CiperUtil.decrypt(ConfigCenterConstants.operators, "ec4c9e0e78f76a69");

    private String parentReadOnlyPath = "/test";
    private String readOnlyPath = parentReadOnlyPath + "/readonly";
    private String readOnlyPathA = parentReadOnlyPath + "/readonlyA";

    private String writablePath = parentReadOnlyPath + "/writable";

    private String removePath = parentReadOnlyPath + "/removePath";

    private String readonlyValue = "test a ttttAAAA";
    private String modifyValue = "testBBBBB";

    @Test(expected = IllegalArgumentException.class)
    public void testNoParameterThrows() throws ConfigException {
        CCSComponentFactory.getConfigClient(configAddr, "admin", "");
    }

    @Before
    public void init() throws Exception {
      //获取zookeeper的client客户端
        ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName);
      //利用客户端创建用户只读节点
        client.createNode(ConfigSDKUtil.appendUserReadOnlyPathPath(userName), ConfigSDKUtil.createReadOnlyACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "test a tttt", CreateMode.PERSISTENT);
       //判断用户只读节点一定要存在      
        assertTrue(client.exists(ConfigSDKUtil.appendUserReadOnlyPathPath(userName)));
        //在用户节点地下，创建一个可读写节点
        client.createNode(ConfigSDKUtil.appendUserWritablePathPath(userName), ConfigSDKUtil.createWritableACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), "test a tttt", CreateMode.PERSISTENT);
        //判断创建的可读写节点一定存在
        assertTrue(client.exists(ConfigSDKUtil.appendUserWritablePathPath(userName)));

       //在用户节点底下，创建一个只读节点readOnlyPath
        client.createNode(ConfigSDKUtil.appendUserReadOnlyPathPath(userName) + readOnlyPath, ConfigSDKUtil.createReadOnlyACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), readonlyValue, CreateMode.PERSISTENT);
        //判断创建的只读节点一定要存在
        assertTrue(client.exists(ConfigSDKUtil.appendUserReadOnlyPathPath(userName) + readOnlyPath));

        //在用户节点底下，创建一个只读节点readonlyValue
        client.createNode(ConfigSDKUtil.appendUserReadOnlyPathPath(userName) + readOnlyPathA, ConfigSDKUtil.createReadOnlyACL(userName,
                String.valueOf(userPwd), adminName, adminPwd), readonlyValue, CreateMode.PERSISTENT);
        //判断创建的只读节点一定要存在
        assertTrue(client.exists(ConfigSDKUtil.appendUserReadOnlyPathPath(userName) + readOnlyPathA));

        createWritableNode();
    }

    //用SDK-API创建一个读写节点
    private void createWritableNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.add(writablePath, writableValue);
        //判断只读节点下一定存在刚才创建的节点
        assertTrue(componentClient.exists(writablePath, ConfigPathMode.WRITABLE));
        //读写节点，在只读节点下找不到
        assertFalse(componentClient.exists(writablePath, ConfigPathMode.READONLY));
        //这个节点的值一定是刚才的赋值
        assertEquals(writableValue, componentClient.get(writablePath, ConfigPathMode.WRITABLE));

        //再创建一个读写节点--下面用例要用来删除
        componentClient.add(removePath, writableValue);
        assertTrue(componentClient.exists(removePath, ConfigPathMode.WRITABLE));
        //读写节点，在只读节点下，应该找不到
        assertFalse(componentClient.exists(removePath, ConfigPathMode.READONLY));
        assertEquals(writableValue, componentClient.get(removePath, ConfigPathMode.WRITABLE));
    }

    @Test
    //读取只读节点-并验证正确性
    public void testGetReadOnlyNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        assertEquals(readonlyValue, componentClient.get(readOnlyPath, ConfigPathMode.READONLY));
    }

    @Test
    //判断刚才创建的只读节点，是否存在，调用另一种exists
    public void testExistReadOnlyNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        assertTrue(componentClient.exists(readOnlyPath));
    }

    @Test
  //判断刚才创建的只读节点，是否存在，调用另一种exists
    public void testExistReadOnlyWithConfigPathModeNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        assertTrue(componentClient.exists(readOnlyPath, ConfigPathMode.READONLY));
    }


    @Test
    //判断不存在的只读节点，是否存在，调用另一种exists
    public void testNoExistReadOnlyWithConfigPathModeNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        assertFalse(componentClient.exists(readOnlyPath + 1, ConfigPathMode.READONLY));
    }

    @Test
  //判断不存在的只读节点，是否存在，
    public void testNoExistReadOnlyNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        assertFalse(componentClient.exists(readOnlyPath + 1));
    }

    @Test
    //修改存在的节点
    public void testModifyNodeByStringValue() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.modify(writablePath, modifyValue);

        assertEquals(modifyValue, componentClient.get(writablePath, ConfigPathMode.WRITABLE));
    }

    @Test(expected = ConfigException.class)
    //修改不存在的节点，抛异常 ConfigException.class
    public void testModifyNoExistsNodeByStringValue() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.modify(writablePath + 1, modifyValue);
    }

    @Test
    //节点类型有两种：字符创&数组。把string值修改成byte数组的值
    public void testModifyNodeByBytesValue() throws ConfigException {
        byte[] data = new byte[]{110, 10, 1, 20, 30};
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.modify(writablePath, data);

        assertArrayEquals(data, componentClient.readBytes(writablePath, ConfigPathMode.WRITABLE));
    }

    @Test(expected = ConfigException.class)
    //修改不存在的，抛异常
    public void testModifyNoExistsNodeByBytesValue() throws ConfigException {
        byte[] data = new byte[]{110, 10, 1, 20, 30};
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.modify(writablePath + 1, data);
    }


    @Test
    //读取子节点数量 --欠一个判断子节点与创建节点一样
    public void testReadOnlySubChild() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        List<String> childrenPath = componentClient.listSubPath(parentReadOnlyPath);

        assertEquals(2, childrenPath.size());
    }

    @Test(expected = ConfigException.class)
    //查看不存在子节点的节点，会抛异常
    public void testReadOnlyNoExistNodeSubChild() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        List<String> childrenPath = componentClient.listSubPath(parentReadOnlyPath + 1);
    }

    @Test
    //查看不存在子节点的节点：0
    public void testReadOnlyNoSubChild() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        List<String> childrenPath = componentClient.listSubPath(readOnlyPath);

        assertNotNull(childrenPath);
        assertEquals(0, childrenPath.size());
    }

    @Test(expected = ConfigException.class)
    //增加一个已存在的读写节点，抛异常
    public void testAddExistNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.add(writablePath, "testValue");
    }

    @Test
    //查看读写节点底下节点的子节点--欠验证节点是否与创建相等
    public void testWritableSubChild() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        List<String> childrenPath = componentClient.listSubPath(parentReadOnlyPath, ConfigPathMode.WRITABLE);
        assertEquals(2, childrenPath.size());
    }

    @Test(expected = ConfigException.class)
    //测试不存在的读写节点的读取，抛异常
    public void testWritableNoExistsNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        List<String> childrenPath = componentClient.listSubPath(parentReadOnlyPath + 1, ConfigPathMode.WRITABLE);
    }

    @Test
    //删除节点
    public void testRemove() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.remove(removePath);
        assertFalse(componentClient.exists(removePath, ConfigPathMode.WRITABLE));
    }
    
    @Test
    //删除节点:删除父节点，验证父节点和子节点都不存在
    public void testRemoveParentNode() throws ConfigException {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.remove(parentReadOnlyPath);
        assertFalse(componentClient.exists(parentReadOnlyPath, ConfigPathMode.WRITABLE));
        assertFalse(componentClient.exists(removePath, ConfigPathMode.WRITABLE));
    }
    
    @After
    public void clearData() throws Exception {
        ZKClient client = ZKPoolFactory.getZKPool(configAddr, adminName, adminPwd).getZkClient(configAddr, adminName).addAuth("digest",
                userName + ":" + String.valueOf(userPwd));
        client.deleteNode(ConfigCenterConstants.UserNodePrefix.FOR_PAAS_PLATFORM_PREFIX + ConfigCenterConstants.SEPARATOR + userName);
        assertFalse(client.exists(ConfigCenterConstants.UserNodePrefix.FOR_PAAS_PLATFORM_PREFIX + ConfigCenterConstants.SEPARATOR + userName));
    }


    @Test
    public void testAdd() throws Exception {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.get(parentReadOnlyPath, ConfigPathMode.WRITABLE, new ConfigWatcher() {
            @Override
            public void processEvent(ConfigWatcherEvent event) {
                System.out.println("AAABBB");
            }
        });


        componentClient.get(parentReadOnlyPath, ConfigPathMode.WRITABLE, new ConfigWatcher() {
            @Override
            public void processEvent(ConfigWatcherEvent event) {
                System.out.println("AAABBB");
            }
        });
    }

    @Test
    public void testListSub() throws Exception {
        ICCSComponent componentClient = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
        componentClient.listSubPath(parentReadOnlyPath, ConfigPathMode.WRITABLE, new ConfigWatcher() {
            @Override
            public void processEvent(ConfigWatcherEvent event) {
                System.out.println("AAABBB");
            }
        });


        componentClient.listSubPath(parentReadOnlyPath, ConfigPathMode.WRITABLE, new ConfigWatcher() {
            @Override
            public void processEvent(ConfigWatcherEvent event) {
                System.out.println("AAABBB");
            }
        });
    }


}
