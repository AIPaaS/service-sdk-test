package test.com.ai.paas.ipaas.mds.messagesender;
import test.com.ai.paas.ipaas.mds.messagesender.base.MessageSender;
import com.ai.paas.ipaas.mds.IMessageSender;
import org.junit.Before;
import org.junit.Test;
public class SendStrStr3Test extends MessageSender{
private IMessageSender iMessageSender  = null;

@Before
public void setUp() throws Exception {
iMessageSender = super.getClient();
}

/*** 正常情况测试*/
@Test
public void send()  {
String str0 = "thenormaltest-str67540";
long long1 = 10000000l;
String str2 = "thenormaltest-str89151";
iMessageSender.send(str0,long1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendFirstNull()  {
String str0 = null;
long long1 = 10000000l;
String str2 = "thenormaltest-str31813";
iMessageSender.send(str0,long1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendSecondNull()  {
String str0 = "thenormaltest-str23200";
long long1 = 0l;
String str2 = "thenormaltest-str21326";
iMessageSender.send(str0,long1,str2);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendThirdNull()  {
String str0 = "thenormaltest-str25664";
long long1 = 10000000l;
String str2 = null;
iMessageSender.send(str0,long1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendFirstBlank()  {
String str0 = "";
long long1 = 10000000l;
String str2 = "thenormaltest-str14174";
iMessageSender.send(str0,long1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendSecondBlank()  {
String str0 = "thenormaltest-str40264";
long long1 = 0l;
String str2 = "thenormaltest-str33535";
iMessageSender.send(str0,long1,str2);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendThirdBlank()  {
String str0 = "thenormaltest-str88702";
long long1 = 10000000l;
String str2 = "";
iMessageSender.send(str0,long1,str2);
}

}
