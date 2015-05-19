package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HgetBytByt2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hget()  {
byte[] byte0 = "thenormaltest-byte43484".getBytes();
byte[] byte1 = "thenormaltest-byte31454".getBytes();
iCacheClient.hget(byte0,byte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hgetFirstNull()  {
byte[] byte0 = null;
byte[] byte1 = "thenormaltest-byte88932".getBytes();
iCacheClient.hget(byte0,byte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hgetSecondNull()  {
byte[] byte0 = "thenormaltest-byte30684".getBytes();
byte[] byte1 = null;
iCacheClient.hget(byte0,byte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hgetFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] byte1 = "thenormaltest-byte30117".getBytes();
iCacheClient.hget(byte0,byte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hgetSecondBlank()  {
byte[] byte0 = "thenormaltest-byte12196".getBytes();
byte[] byte1 = new byte[0];
iCacheClient.hget(byte0,byte1);
}

}
