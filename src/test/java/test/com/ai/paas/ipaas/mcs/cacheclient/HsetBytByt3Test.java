package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HsetBytByt3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hset()  {
byte[] byte0 = "thenormaltest-byte64065".getBytes();
byte[] byte1 = "thenormaltest-byte88266".getBytes();
byte[] byte2 = "thenormaltest-byte25889".getBytes();
iCacheClient.hset(byte0,byte1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetFirstNull()  {
byte[] byte0 = null;
byte[] byte1 = "thenormaltest-byte48434".getBytes();
byte[] byte2 = "thenormaltest-byte18574".getBytes();
iCacheClient.hset(byte0,byte1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetSecondNull()  {
byte[] byte0 = "thenormaltest-byte99659".getBytes();
byte[] byte1 = null;
byte[] byte2 = "thenormaltest-byte87431".getBytes();
iCacheClient.hset(byte0,byte1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetThirdNull()  {
byte[] byte0 = "thenormaltest-byte34018".getBytes();
byte[] byte1 = "thenormaltest-byte78342".getBytes();
byte[] byte2 = null;
iCacheClient.hset(byte0,byte1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] byte1 = "thenormaltest-byte46434".getBytes();
byte[] byte2 = "thenormaltest-byte53620".getBytes();
iCacheClient.hset(byte0,byte1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetSecondBlank()  {
byte[] byte0 = "thenormaltest-byte69757".getBytes();
byte[] byte1 = new byte[0];
byte[] byte2 = "thenormaltest-byte47660".getBytes();
iCacheClient.hset(byte0,byte1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetThirdBlank()  {
byte[] byte0 = "thenormaltest-byte19266".getBytes();
byte[] byte1 = "thenormaltest-byte71016".getBytes();
byte[] byte2 = new byte[0];
iCacheClient.hset(byte0,byte1,byte2);
}

}
