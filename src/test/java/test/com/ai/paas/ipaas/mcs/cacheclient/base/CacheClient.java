package test.com.ai.paas.ipaas.mcs.cacheclient.base;

import com.ai.paas.ipaas.mcs.CacheFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class CacheClient {
	private final String AUTH_ADDR = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private AuthDescriptor ad = null;
	private ICacheClient iCacheClient = null;

	public ICacheClient getClient() throws Exception {
		ad = new AuthDescriptor(AUTH_ADDR, "393170232@qq.com", "123456",
				"MCS001");
		try {
			iCacheClient = CacheFactory.getClient(ad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iCacheClient;
	}
}
