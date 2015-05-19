package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class DecrByBytLong2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void decrBy()  {
byte[] byte0 = "thenormaltest-byte17736".getBytes();
long long1 = 10000000l;
iCacheClient.decrBy(byte0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void decrByFirstNull()  {
byte[] byte0 = null;
long long1 = 10000000l;
iCacheClient.decrBy(byte0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void decrBySecondNull()  {
byte[] byte0 = "thenormaltest-byte15474".getBytes();
long long1 = 0l;
iCacheClient.decrBy(byte0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void decrByFirstBlank()  {
byte[] byte0 = new byte[0];
long long1 = 10000000l;
iCacheClient.decrBy(byte0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void decrBySecondBlank()  {
byte[] byte0 = "thenormaltest-byte60669".getBytes();
long long1 = 0l;
iCacheClient.decrBy(byte0,long1);
}

}
