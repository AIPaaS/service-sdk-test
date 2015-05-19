package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class DelStrs1Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void del()  {
String astr0 = "thenormaltest-str197201";
String bstr0 = "thenormaltest-str239504";
iCacheClient.del(astr0,bstr0);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void delFirstNull()  {
String astr0 = null;
String bstr0 = null;
iCacheClient.del(astr0,bstr0);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void delFirstBlank()  {
String astr0 = "";
String bstr0 = "";
iCacheClient.del(astr0,bstr0);
}

}
