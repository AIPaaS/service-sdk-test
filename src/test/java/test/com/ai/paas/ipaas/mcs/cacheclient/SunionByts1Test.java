package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SunionByts1Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void sunion()  {
byte[] abyte0 = "thenormaltest-bytes114393".getBytes();
byte[] bbyte0 = "thenormaltest-bytes283709".getBytes();
iCacheClient.sunion(abyte0,bbyte0);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sunionFirstNull()  {
byte[] abyte0 = null;
byte[] bbyte0 = null;
iCacheClient.sunion(abyte0,bbyte0);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sunionFirstBlank()  {
byte[] abyte0 = new byte[0];
byte[] bbyte0 = new byte[0];
iCacheClient.sunion(abyte0,bbyte0);
}

}
