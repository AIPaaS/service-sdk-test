package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HexistsStrStr2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hexists()  {
String str0 = "thenormaltest-str84027";
String str1 = "thenormaltest-str85218";
iCacheClient.hexists(str0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hexistsFirstNull()  {
String str0 = null;
String str1 = "thenormaltest-str10356";
iCacheClient.hexists(str0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hexistsSecondNull()  {
String str0 = "thenormaltest-str62201";
String str1 = null;
iCacheClient.hexists(str0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hexistsFirstBlank()  {
String str0 = "";
String str1 = "thenormaltest-str84658";
iCacheClient.hexists(str0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hexistsSecondBlank()  {
String str0 = "thenormaltest-str73956";
String str1 = "";
iCacheClient.hexists(str0,str1);
}

}
