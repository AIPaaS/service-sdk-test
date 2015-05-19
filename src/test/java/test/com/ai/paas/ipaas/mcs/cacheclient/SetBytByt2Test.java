package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SetBytByt2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void set()  {
byte[] byte0 = "thenormaltest-byte20112".getBytes();
byte[] byte1 = "thenormaltest-byte70028".getBytes();
iCacheClient.set(byte0,byte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setFirstNull()  {
byte[] byte0 = null;
byte[] byte1 = "thenormaltest-byte63061".getBytes();
iCacheClient.set(byte0,byte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setSecondNull()  {
byte[] byte0 = "thenormaltest-byte60703".getBytes();
byte[] byte1 = null;
iCacheClient.set(byte0,byte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] byte1 = "thenormaltest-byte85116".getBytes();
iCacheClient.set(byte0,byte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setSecondBlank()  {
byte[] byte0 = "thenormaltest-byte63675".getBytes();
byte[] byte1 = new byte[0];
iCacheClient.set(byte0,byte1);
}

}
