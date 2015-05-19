package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class LpushStrStrs2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void lpush()  {
String str0 = "thenormaltest-str15701";
String astr1 = "thenormaltest-str157902";
String bstr1 = "thenormaltest-str232190";
iCacheClient.lpush(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpushFirstNull()  {
String str0 = null;
String astr1 = "thenormaltest-str185695";
String bstr1 = "thenormaltest-str242410";
iCacheClient.lpush(str0,astr1,bstr1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpushSecondNull()  {
String str0 = "thenormaltest-str41877";
String astr1 = null;
String bstr1 = null;
iCacheClient.lpush(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpushFirstBlank()  {
String str0 = "";
String astr1 = "thenormaltest-str132841";
String bstr1 = "thenormaltest-str253792";
iCacheClient.lpush(str0,astr1,bstr1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void lpushSecondBlank()  {
String str0 = "thenormaltest-str16340";
String astr1 = "";
String bstr1 = "";
iCacheClient.lpush(str0,astr1,bstr1);
}

}
