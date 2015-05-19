package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HgetStrStr2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hget()  {
String str0 = "thenormaltest-str38779";
String str1 = "thenormaltest-str93358";
iCacheClient.hget(str0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hgetFirstNull()  {
String str0 = null;
String str1 = "thenormaltest-str79162";
iCacheClient.hget(str0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hgetSecondNull()  {
String str0 = "thenormaltest-str48097";
String str1 = null;
iCacheClient.hget(str0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hgetFirstBlank()  {
String str0 = "";
String str1 = "thenormaltest-str13358";
iCacheClient.hget(str0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hgetSecondBlank()  {
String str0 = "thenormaltest-str97540";
String str1 = "";
iCacheClient.hget(str0,str1);
}

}
