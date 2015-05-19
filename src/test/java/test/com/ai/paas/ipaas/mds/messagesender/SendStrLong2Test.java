package test.com.ai.paas.ipaas.mds.messagesender;
import test.com.ai.paas.ipaas.mds.messagesender.base.MessageSender;
import com.ai.paas.ipaas.mds.IMessageSender;
import org.junit.Before;
import org.junit.Test;
public class SendStrLong2Test extends MessageSender{
private IMessageSender iMessageSender  = null;

@Before
public void setUp() throws Exception {
iMessageSender = super.getClient();
}

/*** 正常情况测试*/
@Test
public void send()  {
String str0 = "thenormaltest-str36205";
long long1 = 10000000l;
iMessageSender.send(str0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendFirstNull()  {
String str0 = null;
long long1 = 10000000l;
iMessageSender.send(str0,long1);
}

/*** null测试*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendSecondNull()  {
String str0 = "thenormaltest-str63876";
long long1 = 0l;
iMessageSender.send(str0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendFirstBlank()  {
String str0 = "";
long long1 = 10000000l;
iMessageSender.send(str0,long1);
}

/*** 空对象*/
@Test(expected = com.ai.paas.ipaas.mds.MessageClientException.class)
public void sendSecondBlank()  {
String str0 = "thenormaltest-str57172";
long long1 = 0l;
iMessageSender.send(str0,long1);
}

}
