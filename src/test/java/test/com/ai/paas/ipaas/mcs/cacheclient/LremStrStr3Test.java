package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class LremStrStr3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void lrem()  {
String str0 = "thenormaltest-str66919";
long long1 = 10000000l;
String str2 = "thenormaltest-str82871";
iCacheClient.lrem(str0,long1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremFirstNull()  {
String str0 = null;
long long1 = 10000000l;
String str2 = "thenormaltest-str95131";
iCacheClient.lrem(str0,long1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremSecondNull()  {
String str0 = "thenormaltest-str24885";
long long1 = 0l;
String str2 = "thenormaltest-str34557";
iCacheClient.lrem(str0,long1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremThirdNull()  {
String str0 = "thenormaltest-str29142";
long long1 = 10000000l;
String str2 = null;
iCacheClient.lrem(str0,long1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremFirstBlank()  {
String str0 = "";
long long1 = 10000000l;
String str2 = "thenormaltest-str31343";
iCacheClient.lrem(str0,long1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremSecondBlank()  {
String str0 = "thenormaltest-str65446";
long long1 = 0l;
String str2 = "thenormaltest-str33697";
iCacheClient.lrem(str0,long1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lremThirdBlank()  {
String str0 = "thenormaltest-str82930";
long long1 = 10000000l;
String str2 = "";
iCacheClient.lrem(str0,long1,str2);
}

}
