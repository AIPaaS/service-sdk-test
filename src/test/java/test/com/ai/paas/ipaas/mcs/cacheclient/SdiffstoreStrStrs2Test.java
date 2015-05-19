package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SdiffstoreStrStrs2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void sdiffstore()  {
String str0 = "thenormaltest-str38887";
String astr1 = "thenormaltest-str165692";
String bstr1 = "thenormaltest-str256961";
iCacheClient.sdiffstore(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffstoreFirstNull()  {
String str0 = null;
String astr1 = "thenormaltest-str148596";
String bstr1 = "thenormaltest-str249751";
iCacheClient.sdiffstore(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffstoreSecondNull()  {
String str0 = "thenormaltest-str78018";
String astr1 = null;
String bstr1 = null;
iCacheClient.sdiffstore(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffstoreFirstBlank()  {
String str0 = "";
String astr1 = "thenormaltest-str169121";
String bstr1 = "thenormaltest-str294657";
iCacheClient.sdiffstore(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sdiffstoreSecondBlank()  {
String str0 = "thenormaltest-str39290";
String astr1 = "";
String bstr1 = "";
iCacheClient.sdiffstore(str0,astr1,bstr1);
}

}
