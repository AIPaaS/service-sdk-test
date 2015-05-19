package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HsetnxBytByt3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hsetnx()  {
byte[] byte0 = "thenormaltest-byte21220".getBytes();
byte[] byte1 = "thenormaltest-byte95589".getBytes();
byte[] byte2 = "thenormaltest-byte72620".getBytes();
iCacheClient.hsetnx(byte0,byte1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxFirstNull()  {
byte[] byte0 = null;
byte[] byte1 = "thenormaltest-byte19779".getBytes();
byte[] byte2 = "thenormaltest-byte45122".getBytes();
iCacheClient.hsetnx(byte0,byte1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxSecondNull()  {
byte[] byte0 = "thenormaltest-byte26083".getBytes();
byte[] byte1 = null;
byte[] byte2 = "thenormaltest-byte76028".getBytes();
iCacheClient.hsetnx(byte0,byte1,byte2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxThirdNull()  {
byte[] byte0 = "thenormaltest-byte77053".getBytes();
byte[] byte1 = "thenormaltest-byte82966".getBytes();
byte[] byte2 = null;
iCacheClient.hsetnx(byte0,byte1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxFirstBlank()  {
byte[] byte0 = new byte[0];
byte[] byte1 = "thenormaltest-byte56454".getBytes();
byte[] byte2 = "thenormaltest-byte37411".getBytes();
iCacheClient.hsetnx(byte0,byte1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxSecondBlank()  {
byte[] byte0 = "thenormaltest-byte70250".getBytes();
byte[] byte1 = new byte[0];
byte[] byte2 = "thenormaltest-byte43095".getBytes();
iCacheClient.hsetnx(byte0,byte1,byte2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxThirdBlank()  {
byte[] byte0 = "thenormaltest-byte29218".getBytes();
byte[] byte1 = "thenormaltest-byte66051".getBytes();
byte[] byte2 = new byte[0];
iCacheClient.hsetnx(byte0,byte1,byte2);
}

}
