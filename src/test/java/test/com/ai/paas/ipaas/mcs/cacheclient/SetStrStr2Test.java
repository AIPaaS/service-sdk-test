package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SetStrStr2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void set()  {
String str0 = "thenormaltest-str39567";
String str1 = "thenormaltest-str18577";
iCacheClient.set(str0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setFirstNull()  {
String str0 = null;
String str1 = "thenormaltest-str67127";
iCacheClient.set(str0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setSecondNull()  {
String str0 = "thenormaltest-str34044";
String str1 = null;
iCacheClient.set(str0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setFirstBlank()  {
String str0 = "";
String str1 = "thenormaltest-str63209";
iCacheClient.set(str0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setSecondBlank()  {
String str0 = "thenormaltest-str83298";
String str1 = "";
iCacheClient.set(str0,str1);
}

}
