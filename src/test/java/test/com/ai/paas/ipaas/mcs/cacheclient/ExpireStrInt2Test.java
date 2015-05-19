package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class ExpireStrInt2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void expire()  {
String str0 = "thenormaltest-str75113";
int int1 = 100;
iCacheClient.expire(str0,int1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void expireFirstNull()  {
String str0 = null;
int int1 = 100;
iCacheClient.expire(str0,int1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void expireSecondNull()  {
String str0 = "thenormaltest-str73254";
int int1 = 0;
iCacheClient.expire(str0,int1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void expireFirstBlank()  {
String str0 = "";
int int1 = 100;
iCacheClient.expire(str0,int1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void expireSecondBlank()  {
String str0 = "thenormaltest-str74872";
int int1 = 0;
iCacheClient.expire(str0,int1);
}

}
