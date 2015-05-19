package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class LremBytByt3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void lrem()  {
byte[] byte0 = "thenormaltest-byte59959".getBytes();
long long1 = 10000000l;
byte[] byte2 = "thenormaltest-byte51462".getBytes();
iCacheClient.lrem(byte0,long1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremFirstNull()  {
byte[] byte0 = null;
long long1 = 10000000l;
byte[] byte2 = "thenormaltest-byte67798".getBytes();
iCacheClient.lrem(byte0,long1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremSecondNull()  {
byte[] byte0 = "thenormaltest-byte40896".getBytes();
long long1 = 0l;
byte[] byte2 = "thenormaltest-byte90715".getBytes();
iCacheClient.lrem(byte0,long1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremThirdNull()  {
byte[] byte0 = "thenormaltest-byte46467".getBytes();
long long1 = 10000000l;
byte[] byte2 = null;
iCacheClient.lrem(byte0,long1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremFirstBlank()  {
byte[] byte0 = new byte[0];
long long1 = 10000000l;
byte[] byte2 = "thenormaltest-byte76200".getBytes();
iCacheClient.lrem(byte0,long1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremSecondBlank()  {
byte[] byte0 = "thenormaltest-byte23530".getBytes();
long long1 = 0l;
byte[] byte2 = "thenormaltest-byte71087".getBytes();
iCacheClient.lrem(byte0,long1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremThirdBlank()  {
byte[] byte0 = "thenormaltest-byte85300".getBytes();
long long1 = 10000000l;
byte[] byte2 = new byte[0];
iCacheClient.lrem(byte0,long1,byte2);
}

}
