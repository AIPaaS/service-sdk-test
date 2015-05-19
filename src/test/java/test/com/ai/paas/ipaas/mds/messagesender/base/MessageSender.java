package test.com.ai.paas.ipaas.mds.messagesender.base;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
public class MessageSender{
private final String AUTH_ADDR = "http://10.1.228.198:14821/iPaas-Auth/service/check";
private AuthDescriptor ad = null;
private IMessageSender iMessageSender  = null;
public IMessageSender getClient() throws Exception {
//TODO
return iMessageSender;
}
}
