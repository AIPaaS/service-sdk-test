package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class ExpireBytInt2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void expire()  {
byte[] byte0 = "thenormaltest-byte26127".getBytes();
int int1 = 100;
iCacheClient.expire(byte0,int1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void expireFirstNull()  {
byte[] byte0 = null;
int int1 = 100;
iCacheClient.expire(byte0,int1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void expireSecondNull()  {
byte[] byte0 = "thenormaltest-byte93184".getBytes();
int int1 = 0;
iCacheClient.expire(byte0,int1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void expireFirstBlank()  {
byte[] byte0 = new byte[0];
int int1 = 100;
iCacheClient.expire(byte0,int1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void expireSecondBlank()  {
byte[] byte0 = "thenormaltest-byte84247".getBytes();
int int1 = 0;
iCacheClient.expire(byte0,int1);
}

}
