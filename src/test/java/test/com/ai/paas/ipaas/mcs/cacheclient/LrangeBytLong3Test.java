package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class LrangeBytLong3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void lrange()  {
byte[] byte0 = "thenormaltest-byte43944".getBytes();
long long1 = 10000000l;
long long2 = 10000000l;
iCacheClient.lrange(byte0,long1,long2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeFirstNull()  {
byte[] byte0 = null;
long long1 = 10000000l;
long long2 = 10000000l;
iCacheClient.lrange(byte0,long1,long2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeSecondNull()  {
byte[] byte0 = "thenormaltest-byte16998".getBytes();
long long1 = 0l;
long long2 = 10000000l;
iCacheClient.lrange(byte0,long1,long2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeThirdNull()  {
byte[] byte0 = "thenormaltest-byte81363".getBytes();
long long1 = 10000000l;
long long2 = 0l;
iCacheClient.lrange(byte0,long1,long2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeFirstBlank()  {
byte[] byte0 = new byte[0];
long long1 = 10000000l;
long long2 = 10000000l;
iCacheClient.lrange(byte0,long1,long2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeSecondBlank()  {
byte[] byte0 = "thenormaltest-byte42174".getBytes();
long long1 = 0l;
long long2 = 10000000l;
iCacheClient.lrange(byte0,long1,long2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeThirdBlank()  {
byte[] byte0 = "thenormaltest-byte78399".getBytes();
long long1 = 10000000l;
long long2 = 0l;
iCacheClient.lrange(byte0,long1,long2);
}

}
