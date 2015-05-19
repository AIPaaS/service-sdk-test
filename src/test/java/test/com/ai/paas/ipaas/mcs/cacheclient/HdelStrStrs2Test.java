package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HdelStrStrs2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hdel()  {
String str0 = "thenormaltest-str44342";
String astr1 = "thenormaltest-str176412";
String bstr1 = "thenormaltest-str250633";
iCacheClient.hdel(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hdelFirstNull()  {
String str0 = null;
String astr1 = "thenormaltest-str123587";
String bstr1 = "thenormaltest-str244081";
iCacheClient.hdel(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hdelSecondNull()  {
String str0 = "thenormaltest-str75192";
String astr1 = null;
String bstr1 = null;
iCacheClient.hdel(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hdelFirstBlank()  {
String str0 = "";
String astr1 = "thenormaltest-str196570";
String bstr1 = "thenormaltest-str261959";
iCacheClient.hdel(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hdelSecondBlank()  {
String str0 = "thenormaltest-str89981";
String astr1 = "";
String bstr1 = "";
iCacheClient.hdel(str0,astr1,bstr1);
}

}
