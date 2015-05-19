package test.com.ai.paas.ipaas.mcs.cacheclient;
import test.com.ai.paas.ipaas.mcs.cacheclient.base.CacheClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.junit.Before;
import org.junit.Test;
public class IncrByStrLong2Test extends CacheClient{
private ICacheClient iCacheClient  = null;

@Before
public void setUp() throws Exception {
iCacheClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void incrBy()  {
String str0 = "thenormaltest-str70744";
long long1 = 10000000l;
iCacheClient.incrBy(str0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void incrByFirstNull()  {
String str0 = null;
long long1 = 10000000l;
iCacheClient.incrBy(str0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void incrBySecondNull()  {
String str0 = "thenormaltest-str49840";
long long1 = 0l;
iCacheClient.incrBy(str0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void incrByFirstBlank()  {
String str0 = "";
long long1 = 10000000l;
iCacheClient.incrBy(str0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mcs.exception.CacheClientException.class)
public void incrBySecondBlank()  {
String str0 = "thenormaltest-str90697";
long long1 = 0l;
iCacheClient.incrBy(str0,long1);
}

}
