package test.com.ai.paas.ipaas.dss.dssclient;
import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import org.junit.Before;
import org.junit.Test;
public class GetLastUpdateTimeTest extends DSSClient{
private IDSSClient iDSSClient  = null;

@Before
public void setUp() throws Exception {
iDSSClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void getLastUpdateTime()  {
String str0 = "thenormaltest-str81468";
iDSSClient.getLastUpdateTime(str0);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void getLastUpdateTimeFirstNull()  {
String str0 = null;
iDSSClient.getLastUpdateTime(str0);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void getLastUpdateTimeFirstBlank()  {
String str0 = "";
iDSSClient.getLastUpdateTime(str0);
}

}
