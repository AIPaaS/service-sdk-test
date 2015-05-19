package test.com.ai.paas.ipaas.dss.dssclient;
import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import org.junit.Before;
import org.junit.Test;
public class UpdateTest extends DSSClient{
private IDSSClient iDSSClient  = null;

@Before
public void setUp() throws Exception {
iDSSClient = super.getClient();
}

/*** 正常情况测试*/
@Test
public void update()  {
String str0 = "thenormaltest-str74344";
byte[] byte1 = "thenormaltest-byte38184".getBytes();
iDSSClient.update(str0,byte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void updateFirstNull()  {
String str0 = null;
byte[] byte1 = "thenormaltest-byte49185".getBytes();
iDSSClient.update(str0,byte1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void updateSecondNull()  {
String str0 = "thenormaltest-str56263";
byte[] byte1 = null;
iDSSClient.update(str0,byte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void updateFirstBlank()  {
String str0 = "";
byte[] byte1 = "thenormaltest-byte80213".getBytes();
iDSSClient.update(str0,byte1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.dss.exception.DSSRuntimeException.class)
public void updateSecondBlank()  {
String str0 = "thenormaltest-str23301";
byte[] byte1 = new byte[0];
iDSSClient.update(str0,byte1);
}

}
