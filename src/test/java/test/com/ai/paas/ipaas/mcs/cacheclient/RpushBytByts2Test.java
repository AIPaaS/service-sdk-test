package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class RpushBytByts2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void rpush()  {
byte[] byte0 = "thenormaltest-byte12819".getBytes();
byte[] abyte1 = "thenormaltest-bytes182328".getBytes();
byte[] bbyte1 = "thenormaltest-bytes233995".getBytes();
iCacheClient.rpush(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void rpushFirstNull()  {
byte[] byte0 = null;
byte[] abyte1 = "thenormaltest-bytes139558".getBytes();
byte[] bbyte1 = "thenormaltest-bytes290667".getBytes();
iCacheClient.rpush(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void rpushSecondNull()  {
byte[] byte0 = "thenormaltest-byte74027".getBytes();
byte[] abyte1 = null;
byte[] bbyte1 = null;
iCacheClient.rpush(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void rpushFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] abyte1 = "thenormaltest-bytes117899".getBytes();
byte[] bbyte1 = "thenormaltest-bytes295436".getBytes();
iCacheClient.rpush(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void rpushSecondBlank()  {
byte[] byte0 = "thenormaltest-byte66815".getBytes();
byte[] abyte1 = new byte[0];
byte[] bbyte1 = new byte[0];
iCacheClient.rpush(byte0,abyte1,bbyte1);
}

}
