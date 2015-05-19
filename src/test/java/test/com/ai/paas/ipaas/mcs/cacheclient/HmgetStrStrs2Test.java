package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HmgetStrStrs2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hmget()  {
String str0 = "thenormaltest-str92521";
String astr1 = "thenormaltest-str149265";
String bstr1 = "thenormaltest-str298409";
iCacheClient.hmget(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmgetFirstNull()  {
String str0 = null;
String astr1 = "thenormaltest-str181652";
String bstr1 = "thenormaltest-str210026";
iCacheClient.hmget(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmgetSecondNull()  {
String str0 = "thenormaltest-str18835";
String astr1 = null;
String bstr1 = null;
iCacheClient.hmget(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmgetFirstBlank()  {
String str0 = "";
String astr1 = "thenormaltest-str157641";
String bstr1 = "thenormaltest-str242223";
iCacheClient.hmget(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmgetSecondBlank()  {
String str0 = "thenormaltest-str25661";
String astr1 = "";
String bstr1 = "";
iCacheClient.hmget(str0,astr1,bstr1);
}

}
