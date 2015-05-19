package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SdiffStrs1Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void sdiff()  {
String astr0 = "thenormaltest-str151411";
String bstr0 = "thenormaltest-str296646";
iCacheClient.sdiff(astr0,bstr0);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffFirstNull()  {
String astr0 = null;
String bstr0 = null;
iCacheClient.sdiff(astr0,bstr0);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffFirstBlank()  {
String astr0 = "";
String bstr0 = "";
iCacheClient.sdiff(astr0,bstr0);
}

}
