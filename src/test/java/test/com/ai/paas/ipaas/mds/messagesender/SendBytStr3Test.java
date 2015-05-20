package test.com.ai.paas.ipaas.mds.messagesender;
import test.com.ai.paas.ipaas.mds.messagesender.base.MessageSender;
import com.ai.paas.ipaas.mds.IMessageSender;
import org.junit.Before;
import org.junit.Test;
public class SendBytStr3Test extends MessageSender{
private IMessageSender iMessageSender  = null;

@Before
public void setUp() throws Exception {
iMessageSender = super.getClient();
}

/*** 正常情况测试*/
@Test
public void send()  {
byte[] byte0 = "thenormaltest-byte31833".getBytes();
long long1 = 10000000l;
String str2 = "thenormaltest-str47319";
iMessageSender.send(byte0,long1,str2);
}

/*** null测试*/
@Test(expected = IllegalArgumentException.class)
public void sendFirstNull()  {
byte[] byte0 = null;
long long1 = 10000000l;
String str2 = "thenormaltest-str30054";
iMessageSender.send(byte0,long1,str2);
}

/*** null测试*/
@Test(expected = IllegalArgumentException.class)
public void sendSecondNull()  {
byte[] byte0 = "thenormaltest-byte79360".getBytes();
long long1 = 0l;
String str2 = "thenormaltest-str69764";
iMessageSender.send(byte0,long1,str2);
}

/*** null测试*/
@Test(expected = IllegalArgumentException.class)
public void sendThirdNull()  {
byte[] byte0 = "thenormaltest-byte58229".getBytes();
long long1 = 10000000l;
String str2 = null;
iMessageSender.send(byte0,long1,str2);
}

/*** 空对象*/
@Test(expected = IllegalArgumentException.class)
public void sendFirstBlank()  {
byte[] byte0 = new byte[0];
long long1 = 10000000l;
String str2 = "thenormaltest-str44158";
iMessageSender.send(byte0,long1,str2);
}

/*** 空对象*/
@Test(expected = IllegalArgumentException.class)
public void sendSecondBlank()  {
byte[] byte0 = "thenormaltest-byte26467".getBytes();
long long1 = 0l;
String str2 = "thenormaltest-str30452";
iMessageSender.send(byte0,long1,str2);
}

/*** 空对象*/
@Test(expected = IllegalArgumentException.class)
public void sendThirdBlank()  {
byte[] byte0 = "thenormaltest-byte70252".getBytes();
long long1 = 10000000l;
String str2 = "";
iMessageSender.send(byte0,long1,str2);
}

}
