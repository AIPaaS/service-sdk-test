package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class RpushStrStrs2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void rpush()  {
String str0 = "thenormaltest-str30387";
String astr1 = "thenormaltest-str139774";
String bstr1 = "thenormaltest-str213436";
iCacheClient.rpush(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void rpushFirstNull()  {
String str0 = null;
String astr1 = "thenormaltest-str181206";
String bstr1 = "thenormaltest-str282862";
iCacheClient.rpush(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void rpushSecondNull()  {
String str0 = "thenormaltest-str55085";
String astr1 = null;
String bstr1 = null;
iCacheClient.rpush(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void rpushFirstBlank()  {
String str0 = "";
String astr1 = "thenormaltest-str138437";
String bstr1 = "thenormaltest-str257600";
iCacheClient.rpush(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void rpushSecondBlank()  {
String str0 = "thenormaltest-str14869";
String astr1 = "";
String bstr1 = "";
iCacheClient.rpush(str0,astr1,bstr1);
}

}
