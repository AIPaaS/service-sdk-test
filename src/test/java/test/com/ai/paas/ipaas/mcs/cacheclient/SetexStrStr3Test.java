package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SetexStrStr3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void setex()  {
String str0 = "thenormaltest-str32310";
int int1 = 100;
String str2 = "thenormaltest-str94575";
iCacheClient.setex(str0,int1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexFirstNull()  {
String str0 = null;
int int1 = 100;
String str2 = "thenormaltest-str51414";
iCacheClient.setex(str0,int1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexSecondNull()  {
String str0 = "thenormaltest-str55702";
int int1 = 0;
String str2 = "thenormaltest-str41993";
iCacheClient.setex(str0,int1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexThirdNull()  {
String str0 = "thenormaltest-str65672";
int int1 = 100;
String str2 = null;
iCacheClient.setex(str0,int1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexFirstBlank()  {
String str0 = "";
int int1 = 100;
String str2 = "thenormaltest-str68610";
iCacheClient.setex(str0,int1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexSecondBlank()  {
String str0 = "thenormaltest-str94129";
int int1 = 0;
String str2 = "thenormaltest-str81583";
iCacheClient.setex(str0,int1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void setexThirdBlank()  {
String str0 = "thenormaltest-str98867";
int int1 = 100;
String str2 = "";
iCacheClient.setex(str0,int1,str2);
}

}
