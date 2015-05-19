package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SdiffstoreBytByts2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void sdiffstore()  {
byte[] byte0 = "thenormaltest-byte36651".getBytes();
byte[] abyte1 = "thenormaltest-bytes174006".getBytes();
byte[] bbyte1 = "thenormaltest-bytes252604".getBytes();
iCacheClient.sdiffstore(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffstoreFirstNull()  {
byte[] byte0 = null;
byte[] abyte1 = "thenormaltest-bytes183510".getBytes();
byte[] bbyte1 = "thenormaltest-bytes228027".getBytes();
iCacheClient.sdiffstore(byte0,abyte1,bbyte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffstoreSecondNull()  {
byte[] byte0 = "thenormaltest-byte37831".getBytes();
byte[] abyte1 = null;
byte[] bbyte1 = null;
iCacheClient.sdiffstore(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffstoreFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] abyte1 = "thenormaltest-bytes127745".getBytes();
byte[] bbyte1 = "thenormaltest-bytes263459".getBytes();
iCacheClient.sdiffstore(byte0,abyte1,bbyte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffstoreSecondBlank()  {
byte[] byte0 = "thenormaltest-byte81189".getBytes();
byte[] abyte1 = new byte[0];
byte[] bbyte1 = new byte[0];
iCacheClient.sdiffstore(byte0,abyte1,bbyte1);
}

}
