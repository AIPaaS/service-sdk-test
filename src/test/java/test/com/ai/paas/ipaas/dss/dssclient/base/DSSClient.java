package test.com.ai.paas.ipaas.dss.dssclient.base;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
public class DSSClient{
private final String AUTH_ADDR = "http://10.1.228.198:14821/iPaas-Auth/service/check";
private AuthDescriptor ad = null;
private IDSSClient iDSSClient  = null;
public IDSSClient getClient() throws Exception {
//TODO
return iDSSClient;
}
}
