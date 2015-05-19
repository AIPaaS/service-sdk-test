package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SdiffByts1Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void sdiff()  {
byte[] abyte0 = "thenormaltest-bytes165273".getBytes();
byte[] bbyte0 = "thenormaltest-bytes252778".getBytes();
iCacheClient.sdiff(abyte0,bbyte0);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffFirstNull()  {
byte[] abyte0 = null;
byte[] bbyte0 = null;
iCacheClient.sdiff(abyte0,bbyte0);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffFirstBlank()  {
byte[] abyte0 = new byte[0];
byte[] bbyte0 = new byte[0];
iCacheClient.sdiff(abyte0,bbyte0);
}

}
