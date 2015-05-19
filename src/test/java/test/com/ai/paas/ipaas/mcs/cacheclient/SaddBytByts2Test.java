package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SaddBytByts2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void sadd()  {
byte[] byte0 = "thenormaltest-byte22010".getBytes();
byte[] abyte1 = "thenormaltest-bytes118453".getBytes();
byte[] bbyte1 = "thenormaltest-bytes249517".getBytes();
iCacheClient.sadd(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void saddFirstNull()  {
byte[] byte0 = null;
byte[] abyte1 = "thenormaltest-bytes190812".getBytes();
byte[] bbyte1 = "thenormaltest-bytes237436".getBytes();
iCacheClient.sadd(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void saddSecondNull()  {
byte[] byte0 = "thenormaltest-byte94378".getBytes();
byte[] abyte1 = null;
byte[] bbyte1 = null;
iCacheClient.sadd(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void saddFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] abyte1 = "thenormaltest-bytes122903".getBytes();
byte[] bbyte1 = "thenormaltest-bytes292846".getBytes();
iCacheClient.sadd(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void saddSecondBlank()  {
byte[] byte0 = "thenormaltest-byte71510".getBytes();
byte[] abyte1 = new byte[0];
byte[] bbyte1 = new byte[0];
iCacheClient.sadd(byte0,abyte1,bbyte1);
}

}
