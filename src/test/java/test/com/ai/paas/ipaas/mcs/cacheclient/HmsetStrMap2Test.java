package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HmsetStrMap2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hmset()  {
String str0 = "thenormaltest-str77885";
java.util.Map map1 = new java.util.HashMap();
map1.put("12c32" ,"dafda");
iCacheClient.hmset(str0,map1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmsetFirstNull()  {
String str0 = null;
java.util.Map map1 = new java.util.HashMap();
map1.put("123sd2" ,"dafda");
iCacheClient.hmset(str0,map1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmsetSecondNull()  {
String str0 = "thenormaltest-str63101";
java.util.Map map1 = null;
iCacheClient.hmset(str0,map1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmsetFirstBlank()  {
String str0 = "";
java.util.Map map1 = new java.util.HashMap();
map1.put("1231232" ,"dafda");
iCacheClient.hmset(str0,map1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hmsetSecondBlank()  {
String str0 = "thenormaltest-str75483";
java.util.Map map1 = new java.util.HashMap();
iCacheClient.hmset(str0,map1);
}

}
