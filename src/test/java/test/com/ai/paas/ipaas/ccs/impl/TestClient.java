package test.com.ai.paas.ipaas.ccs.impl;

import org.junit.Test;

import com.ai.paas.ipaas.ccs.ConfigFactory;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

/**
 * Created by astraea on 2015/5/8.
 */
public class TestClient {

    @Test
    public void test() throws Exception {
        AuthDescriptor ad = new AuthDescriptor();
        ad.setPassword("mvne");
        ad.setServiceId("CCS001");
        ad.setAuthAdress("http://10.1.228.198:14821/iPaas-Auth/service/check");
        ad.setUserName("mvne@asiainfo.com");
        IConfigClient client = ConfigFactory.getConfigClient(ad);
    }

}
