package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class HsetnxStrStr3Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void hsetnx()  {
String str0 = "thenormaltest-str57782";
String str1 = "thenormaltest-str32290";
String str2 = "thenormaltest-str76072";
iCacheClient.hsetnx(str0,str1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxFirstNull()  {
String str0 = null;
String str1 = "thenormaltest-str78142";
String str2 = "thenormaltest-str14323";
iCacheClient.hsetnx(str0,str1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxSecondNull()  {
String str0 = "thenormaltest-str55754";
String str1 = null;
String str2 = "thenormaltest-str38897";
iCacheClient.hsetnx(str0,str1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxThirdNull()  {
String str0 = "thenormaltest-str52791";
String str1 = "thenormaltest-str93629";
String str2 = null;
iCacheClient.hsetnx(str0,str1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxFirstBlank()  {
String str0 = "";
String str1 = "thenormaltest-str17770";
String str2 = "thenormaltest-str15684";
iCacheClient.hsetnx(str0,str1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxSecondBlank()  {
String str0 = "thenormaltest-str98933";
String str1 = "";
String str2 = "thenormaltest-str36935";
iCacheClient.hsetnx(str0,str1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void hsetnxThirdBlank()  {
String str0 = "thenormaltest-str36439";
String str1 = "thenormaltest-str56130";
String str2 = "";
iCacheClient.hsetnx(str0,str1,str2);
}

}
