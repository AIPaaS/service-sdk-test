package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HmgetBytByts2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hmget()  {
byte[] byte0 = "thenormaltest-byte99751".getBytes();
byte[] abyte1 = "thenormaltest-bytes169580".getBytes();
byte[] bbyte1 = "thenormaltest-bytes290482".getBytes();
iCacheClient.hmget(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmgetFirstNull()  {
byte[] byte0 = null;
byte[] abyte1 = "thenormaltest-bytes172764".getBytes();
byte[] bbyte1 = "thenormaltest-bytes256748".getBytes();
iCacheClient.hmget(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmgetSecondNull()  {
byte[] byte0 = "thenormaltest-byte91991".getBytes();
byte[] abyte1 = null;
byte[] bbyte1 = null;
iCacheClient.hmget(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmgetFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] abyte1 = "thenormaltest-bytes124193".getBytes();
byte[] bbyte1 = "thenormaltest-bytes290281".getBytes();
iCacheClient.hmget(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmgetSecondBlank()  {
byte[] byte0 = "thenormaltest-byte14088".getBytes();
byte[] abyte1 = new byte[0];
byte[] bbyte1 = new byte[0];
iCacheClient.hmget(byte0,abyte1,bbyte1);
}

}
