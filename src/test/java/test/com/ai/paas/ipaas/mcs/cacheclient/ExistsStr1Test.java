package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class ExistsStr1Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void exists()  {
String str0 = "thenormaltest-str84314";
iCacheClient.exists(str0);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void existsFirstNull()  {
String str0 = null;
iCacheClient.exists(str0);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void existsFirstBlank()  {
String str0 = "";
iCacheClient.exists(str0);
}

}
