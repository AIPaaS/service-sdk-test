package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HmsetBytMap2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hmset()  {
byte[] byte0 = "thenormaltest-byte52203".getBytes();
java.util.Map map1 = new java.util.HashMap();
map1.put("1232".getBytes(), "dafda".getBytes());
iCacheClient.hmset(byte0,map1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmsetFirstNull()  {
byte[] byte0 = null;
java.util.Map map1 = new java.util.HashMap();
map1.put("1232".getBytes(), "dafda".getBytes());
iCacheClient.hmset(byte0,map1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmsetSecondNull()  {
byte[] byte0 = "thenormaltest-byte33552".getBytes();
java.util.Map map1 = null;
iCacheClient.hmset(byte0,map1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmsetFirstBlank()  {
byte[] byte0 = new byte[0];
java.util.Map map1 = new java.util.HashMap();
map1.put("1232".getBytes(), "dafda".getBytes());
iCacheClient.hmset(byte0,map1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmsetSecondBlank()  {
byte[] byte0 = "thenormaltest-byte52890".getBytes();
java.util.Map map1 = new java.util.HashMap();
iCacheClient.hmset(byte0,map1);
}

}
