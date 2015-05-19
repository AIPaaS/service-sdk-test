package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SremStrStrs2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void srem()  {
String str0 = "thenormaltest-str25170";
String astr1 = "thenormaltest-str164320";
String bstr1 = "thenormaltest-str252883";
iCacheClient.srem(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sremFirstNull()  {
String str0 = null;
String astr1 = "thenormaltest-str198629";
String bstr1 = "thenormaltest-str238497";
iCacheClient.srem(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sremSecondNull()  {
String str0 = "thenormaltest-str73687";
String astr1 = null;
String bstr1 = null;
iCacheClient.srem(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sremFirstBlank()  {
String str0 = "";
String astr1 = "thenormaltest-str165806";
String bstr1 = "thenormaltest-str225455";
iCacheClient.srem(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void sremSecondBlank()  {
String str0 = "thenormaltest-str16323";
String astr1 = "";
String bstr1 = "";
iCacheClient.srem(str0,astr1,bstr1);
}

}
