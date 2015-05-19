package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class SaddStrStrs2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void sadd()  {
String str0 = "thenormaltest-str78371";
String astr1 = "thenormaltest-str173321";
String bstr1 = "thenormaltest-str273505";
iCacheClient.sadd(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void saddFirstNull()  {
String str0 = null;
String astr1 = "thenormaltest-str126727";
String bstr1 = "thenormaltest-str276581";
iCacheClient.sadd(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void saddSecondNull()  {
String str0 = "thenormaltest-str17352";
String astr1 = null;
String bstr1 = null;
iCacheClient.sadd(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void saddFirstBlank()  {
String str0 = "";
String astr1 = "thenormaltest-str155443";
String bstr1 = "thenormaltest-str277644";
iCacheClient.sadd(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void saddSecondBlank()  {
String str0 = "thenormaltest-str46059";
String astr1 = "";
String bstr1 = "";
iCacheClient.sadd(str0,astr1,bstr1);
}

}
