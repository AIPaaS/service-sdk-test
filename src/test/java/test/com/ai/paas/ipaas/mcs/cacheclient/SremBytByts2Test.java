package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SremBytByts2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void srem()  {
byte[] byte0 = "thenormaltest-byte88895".getBytes();
byte[] abyte1 = "thenormaltest-bytes178073".getBytes();
byte[] bbyte1 = "thenormaltest-bytes250615".getBytes();
iCacheClient.srem(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sremFirstNull()  {
byte[] byte0 = null;
byte[] abyte1 = "thenormaltest-bytes190094".getBytes();
byte[] bbyte1 = "thenormaltest-bytes296631".getBytes();
iCacheClient.srem(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sremSecondNull()  {
byte[] byte0 = "thenormaltest-byte33693".getBytes();
byte[] abyte1 = null;
byte[] bbyte1 = null;
iCacheClient.srem(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sremFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] abyte1 = "thenormaltest-bytes184738".getBytes();
byte[] bbyte1 = "thenormaltest-bytes235172".getBytes();
iCacheClient.srem(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sremSecondBlank()  {
byte[] byte0 = "thenormaltest-byte15875".getBytes();
byte[] abyte1 = new byte[0];
byte[] bbyte1 = new byte[0];
iCacheClient.srem(byte0,abyte1,bbyte1);
}

}
