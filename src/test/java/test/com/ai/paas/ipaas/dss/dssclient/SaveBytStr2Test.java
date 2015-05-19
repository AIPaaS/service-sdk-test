package test.com.ai.paas.ipaas.dss.dssclient;
import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import org.junit.Before;
import org.junit.Test;
public class SaveBytStr2Test extends DSSClient{
private IDSSClient iDSSClient  = null;

@Before
public void setUp() throws Exception {
iDSSClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void save()  {
byte[] byte0 = "thenormaltest-byte32157".getBytes();
String str1 = "thenormaltest-str96164";
iDSSClient.save(byte0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void saveFirstNull()  {
byte[] byte0 = null;
String str1 = "thenormaltest-str33812";
iDSSClient.save(byte0,str1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void saveSecondNull()  {
byte[] byte0 = "thenormaltest-byte76559".getBytes();
String str1 = null;
iDSSClient.save(byte0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void saveFirstBlank()  {
byte[] byte0 = new byte[0];
String str1 = "thenormaltest-str80779";
iDSSClient.save(byte0,str1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void saveSecondBlank()  {
byte[] byte0 = "thenormaltest-byte30379".getBytes();
String str1 = "";
iDSSClient.save(byte0,str1);
}

}
