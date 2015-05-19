package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HdelBytByts2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hdel()  {
byte[] byte0 = "thenormaltest-byte93812".getBytes();
byte[] abyte1 = "thenormaltest-bytes184070".getBytes();
byte[] bbyte1 = "thenormaltest-bytes227516".getBytes();
iCacheClient.hdel(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hdelFirstNull()  {
byte[] byte0 = null;
byte[] abyte1 = "thenormaltest-bytes150084".getBytes();
byte[] bbyte1 = "thenormaltest-bytes256705".getBytes();
iCacheClient.hdel(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hdelSecondNull()  {
byte[] byte0 = "thenormaltest-byte37404".getBytes();
byte[] abyte1 = null;
byte[] bbyte1 = null;
iCacheClient.hdel(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hdelFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] abyte1 = "thenormaltest-bytes167508".getBytes();
byte[] bbyte1 = "thenormaltest-bytes220825".getBytes();
iCacheClient.hdel(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hdelSecondBlank()  {
byte[] byte0 = "thenormaltest-byte67165".getBytes();
byte[] abyte1 = new byte[0];
byte[] bbyte1 = new byte[0];
iCacheClient.hdel(byte0,abyte1,bbyte1);
}

}
