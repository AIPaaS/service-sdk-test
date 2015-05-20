package test.com.ai.paas.ipaas.mds.messagesender;
import test.com.ai.paas.ipaas.mds.messagesender.base.MessageSender;
import com.ai.paas.ipaas.mds.IMessageSender;
import org.junit.Before;
import org.junit.Test;
public class SendBytLong2Test extends MessageSender{
private IMessageSender iMessageSender  = null;

@Before
public void setUp() throws Exception {
iMessageSender = super.getClient();
}

/*** 正常情况测试*/
@Test
public void send()  {
byte[] byte0 = "thenormaltest-byte71080".getBytes();
long long1 = 10000000l;
iMessageSender.send(byte0,long1);
}

/*** null测试*/
@Test(expected = IllegalArgumentException.class)
public void sendFirstNull()  {
byte[] byte0 = null;
long long1 = 10000000l;
iMessageSender.send(byte0,long1);
}

/*** null测试*/
@Test(expected = IllegalArgumentException.class)
public void sendSecondNull()  {
byte[] byte0 = "thenormaltest-byte32100".getBytes();
long long1 = 0l;
iMessageSender.send(byte0,long1);
}

/*** 空对象*/
@Test(expected = IllegalArgumentException.class)
public void sendFirstBlank()  {
byte[] byte0 = new byte[0];
long long1 = 10000000l;
iMessageSender.send(byte0,long1);
}

/*** 空对象*/
@Test(expected = IllegalArgumentException.class)
public void sendSecondBlank()  {
byte[] byte0 = "thenormaltest-byte43268".getBytes();
long long1 = 0l;
iMessageSender.send(byte0,long1);
}

}
