package test.com.ai.paas.ipaas.dss.dssclient;
import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import org.junit.Before;
import org.junit.Test;
public class SaveFileStr2Test extends DSSClient{
private IDSSClient iDSSClient  = null;

@Before
public void setUp() throws Exception {
iDSSClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void save()  {
// TODO;
java.io.File file0 = new java.io.File("");
String str1 = "thenormaltest-str64585";
iDSSClient.save(file0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void saveFirstNull()  {
java.io.File file0 = null;
String str1 = "thenormaltest-str21559";
iDSSClient.save(file0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void saveSecondNull()  {
// TODO;
java.io.File file0 = new java.io.File("");
String str1 = null;
iDSSClient.save(file0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void saveFirstBlank()  {
// TODO;
java.io.File file0 = new java.io.File("");
String str1 = "thenormaltest-str72292";
iDSSClient.save(file0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void saveSecondBlank()  {
// TODO;
java.io.File file0 = new java.io.File("");
String str1 = "";
iDSSClient.save(file0,str1);
}

}
