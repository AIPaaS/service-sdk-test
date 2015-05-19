package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HsetStrStr3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hset()  {
String str0 = "thenormaltest-str14063";
String str1 = "thenormaltest-str30805";
String str2 = "thenormaltest-str44596";
iCacheClient.hset(str0,str1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetFirstNull()  {
String str0 = null;
String str1 = "thenormaltest-str83348";
String str2 = "thenormaltest-str16366";
iCacheClient.hset(str0,str1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetSecondNull()  {
String str0 = "thenormaltest-str98243";
String str1 = null;
String str2 = "thenormaltest-str48853";
iCacheClient.hset(str0,str1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetThirdNull()  {
String str0 = "thenormaltest-str73922";
String str1 = "thenormaltest-str77794";
String str2 = null;
iCacheClient.hset(str0,str1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetFirstBlank()  {
String str0 = "";
String str1 = "thenormaltest-str19627";
String str2 = "thenormaltest-str19020";
iCacheClient.hset(str0,str1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetSecondBlank()  {
String str0 = "thenormaltest-str78243";
String str1 = "";
String str2 = "thenormaltest-str30194";
iCacheClient.hset(str0,str1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetThirdBlank()  {
String str0 = "thenormaltest-str51657";
String str1 = "thenormaltest-str62383";
String str2 = "";
iCacheClient.hset(str0,str1,str2);
}

}
