package test.com.ai.paas.ipaas.mcs.cacheclient;

import com.ai.paas.ipaas.mcs.CacheFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class TestSet {
	private static ICacheClient iCacheClient = null;

	static {
		try {
			iCacheClient = CacheFactory.getClient(new AuthDescriptor(
					"http://10.1.228.198:14821/iPaas-Auth/service/check",
					"393170232@qq.com", "123456", "MCS001"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if(args!=null&&args.length>0){
			int num = Integer.valueOf(args[0]);
			
			
		}
			
		
		System.out.println(iCacheClient.set("key_liwx", "fadfadf"));
	}
}
