package test.com.ai.paas.ipaas.dss.dssclient;
import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import org.junit.Before;
import org.junit.Test;
public class ReadTest extends DSSClient{
private IDSSClient iDSSClient  = null;

@Before
public void setUp() throws Exception {
iDSSClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void read()  {
String str0 = "thenormaltest-str97318";
iDSSClient.read(str0);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void readFirstNull()  {
String str0 = null;
iDSSClient.read(str0);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void readFirstBlank()  {
String str0 = "";
iDSSClient.read(str0);
}

}
