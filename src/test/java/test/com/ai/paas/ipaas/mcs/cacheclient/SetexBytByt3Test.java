package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SetexBytByt3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void setex()  {
byte[] byte0 = "thenormaltest-byte50816".getBytes();
int int1 = 100;
byte[] byte2 = "thenormaltest-byte17363".getBytes();
iCacheClient.setex(byte0,int1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexFirstNull()  {
byte[] byte0 = null;
int int1 = 100;
byte[] byte2 = "thenormaltest-byte71950".getBytes();
iCacheClient.setex(byte0,int1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexSecondNull()  {
byte[] byte0 = "thenormaltest-byte60800".getBytes();
int int1 = 0;
byte[] byte2 = "thenormaltest-byte50930".getBytes();
iCacheClient.setex(byte0,int1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexThirdNull()  {
byte[] byte0 = "thenormaltest-byte39451".getBytes();
int int1 = 100;
byte[] byte2 = null;
iCacheClient.setex(byte0,int1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexFirstBlank()  {
byte[] byte0 = new byte[0];
int int1 = 100;
byte[] byte2 = "thenormaltest-byte36145".getBytes();
iCacheClient.setex(byte0,int1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexSecondBlank()  {
byte[] byte0 = "thenormaltest-byte58425".getBytes();
int int1 = 0;
byte[] byte2 = "thenormaltest-byte47947".getBytes();
iCacheClient.setex(byte0,int1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexThirdBlank()  {
byte[] byte0 = "thenormaltest-byte15414".getBytes();
int int1 = 100;
byte[] byte2 = new byte[0];
iCacheClient.setex(byte0,int1,byte2);
}

}
