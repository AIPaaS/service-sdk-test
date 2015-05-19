package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class LrangeStrLong3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void lrange()  {
String str0 = "thenormaltest-str90066";
long long1 = 10000000l;
long long2 = 10000000l;
iCacheClient.lrange(str0,long1,long2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeFirstNull()  {
String str0 = null;
long long1 = 10000000l;
long long2 = 10000000l;
iCacheClient.lrange(str0,long1,long2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeSecondNull()  {
String str0 = "thenormaltest-str23512";
long long1 = 0l;
long long2 = 10000000l;
iCacheClient.lrange(str0,long1,long2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeThirdNull()  {
String str0 = "thenormaltest-str16612";
long long1 = 10000000l;
long long2 = 0l;
iCacheClient.lrange(str0,long1,long2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeFirstBlank()  {
String str0 = "";
long long1 = 10000000l;
long long2 = 10000000l;
iCacheClient.lrange(str0,long1,long2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeSecondBlank()  {
String str0 = "thenormaltest-str84799";
long long1 = 0l;
long long2 = 10000000l;
iCacheClient.lrange(str0,long1,long2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lrangeThirdBlank()  {
String str0 = "thenormaltest-str39294";
long long1 = 10000000l;
long long2 = 0l;
iCacheClient.lrange(str0,long1,long2);
}

}
