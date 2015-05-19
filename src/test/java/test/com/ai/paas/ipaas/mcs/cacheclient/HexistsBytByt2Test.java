package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HexistsBytByt2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hexists()  {
byte[] byte0 = "thenormaltest-byte26465".getBytes();
byte[] byte1 = "thenormaltest-byte92967".getBytes();
iCacheClient.hexists(byte0,byte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hexistsFirstNull()  {
byte[] byte0 = null;
byte[] byte1 = "thenormaltest-byte31428".getBytes();
iCacheClient.hexists(byte0,byte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hexistsSecondNull()  {
byte[] byte0 = "thenormaltest-byte53529".getBytes();
byte[] byte1 = null;
iCacheClient.hexists(byte0,byte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hexistsFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] byte1 = "thenormaltest-byte84151".getBytes();
iCacheClient.hexists(byte0,byte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hexistsSecondBlank()  {
byte[] byte0 = "thenormaltest-byte52432".getBytes();
byte[] byte1 = new byte[0];
iCacheClient.hexists(byte0,byte1);
}

}
