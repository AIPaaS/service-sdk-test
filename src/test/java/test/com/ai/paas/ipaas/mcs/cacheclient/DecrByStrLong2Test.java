package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class DecrByStrLong2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void decrBy()  {
String str0 = "thenormaltest-str34850";
long long1 = 10000000l;
iCacheClient.decrBy(str0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void decrByFirstNull()  {
String str0 = null;
long long1 = 10000000l;
iCacheClient.decrBy(str0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void decrBySecondNull()  {
String str0 = "thenormaltest-str35486";
long long1 = 0l;
iCacheClient.decrBy(str0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void decrByFirstBlank()  {
String str0 = "";
long long1 = 10000000l;
iCacheClient.decrBy(str0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void decrBySecondBlank()  {
String str0 = "thenormaltest-str89892";
long long1 = 0l;
iCacheClient.decrBy(str0,long1);
}

}
