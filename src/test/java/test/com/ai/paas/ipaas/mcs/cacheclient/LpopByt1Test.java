package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class LpopByt1Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void lpop()  {
byte[] byte0 = "thenormaltest-byte40302".getBytes();
iCacheClient.lpop(byte0);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpopFirstNull()  {
byte[] byte0 = null;
iCacheClient.lpop(byte0);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpopFirstBlank()  {
byte[] byte0 = new byte[0];
iCacheClient.lpop(byte0);
}

}
