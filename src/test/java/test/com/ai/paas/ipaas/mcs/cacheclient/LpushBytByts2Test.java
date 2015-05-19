package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class LpushBytByts2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void lpush()  {
byte[] byte0 = "thenormaltest-byte66018".getBytes();
byte[] abyte1 = "thenormaltest-bytes127479".getBytes();
byte[] bbyte1 = "thenormaltest-bytes239070".getBytes();
iCacheClient.lpush(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpushFirstNull()  {
byte[] byte0 = null;
byte[] abyte1 = "thenormaltest-bytes136772".getBytes();
byte[] bbyte1 = "thenormaltest-bytes236455".getBytes();
iCacheClient.lpush(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpushSecondNull()  {
byte[] byte0 = "thenormaltest-byte64629".getBytes();
byte[] abyte1 = null;
byte[] bbyte1 = null;
iCacheClient.lpush(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpushFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] abyte1 = "thenormaltest-bytes154260".getBytes();
byte[] bbyte1 = "thenormaltest-bytes219908".getBytes();
iCacheClient.lpush(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpushSecondBlank()  {
byte[] byte0 = "thenormaltest-byte43954".getBytes();
byte[] abyte1 = new byte[0];
byte[] bbyte1 = new byte[0];
iCacheClient.lpush(byte0,abyte1,bbyte1);
}

}
