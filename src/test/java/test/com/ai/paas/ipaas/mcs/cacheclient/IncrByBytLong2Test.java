package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class IncrByBytLong2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void incrBy()  {
byte[] byte0 = "thenormaltest-byte37424".getBytes();
long long1 = 10000000l;
iCacheClient.incrBy(byte0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void incrByFirstNull()  {
byte[] byte0 = null;
long long1 = 10000000l;
iCacheClient.incrBy(byte0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void incrBySecondNull()  {
byte[] byte0 = "thenormaltest-byte86784".getBytes();
long long1 = 0l;
iCacheClient.incrBy(byte0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void incrByFirstBlank()  {
byte[] byte0 = new byte[0];
long long1 = 10000000l;
iCacheClient.incrBy(byte0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void incrBySecondBlank()  {
byte[] byte0 = "thenormaltest-byte58206".getBytes();
long long1 = 0l;
iCacheClient.incrBy(byte0,long1);
}

}
