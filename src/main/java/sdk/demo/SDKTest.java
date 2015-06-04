package sdk.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import com.ai.paas.ipaas.ccs.ConfigFactory;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.inner.CCSComponentFactory;
import com.ai.paas.ipaas.ccs.inner.ICCSComponent;
import com.ai.paas.ipaas.ccs.inner.constants.ConfigPathMode;
import com.ai.paas.ipaas.dss.DSSFactory;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import com.ai.paas.ipaas.mcs.CacheFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

@SuppressWarnings("rawtypes")
public class SDKTest {

    private static Properties mConfig = new Properties();
    private static String AUTHURL;

	static{
		Class config_class = SDKTest.class;
        try {
			InputStream is = new FileInputStream(new File(config_class.getResource("/config/param.properties").toURI()));
			try {
				mConfig.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			AUTHURL = mConfig.getProperty("AUTHURL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		System.out.println("testCCSIN");
		testCCSIN();
		System.out.println("testCCS");
		testCCS();
		
		System.out.println("testMCS");
		testMCS();
		
		System.out.println("testDSS");
		testDSS();
	}
	private static void testDSS() {
		try {
			String dss = mConfig.getProperty("DSSPARAM");
			AuthDescriptor ad = new AuthDescriptor(AUTHURL, dss.split(",")[0], dss.split(",")[2],
					dss.split(",")[1]);
			IDSSClient iDSSClient = DSSFactory.getClient(ad);
			String id = iDSSClient.save("ok".getBytes(), "test");
			System.out.println("id:"+id);
			System.out.println(iDSSClient.read(id));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	private static void testMCS() {
		try {
			String mcs = mConfig.getProperty("MCSPARAM");
			AuthDescriptor ad = new AuthDescriptor(AUTHURL, mcs.split(",")[0], mcs.split(",")[2],
					mcs.split(",")[1]);
			ICacheClient iCacheClient = CacheFactory.getClient(ad);
			iCacheClient.set("hello", "ok");
			System.out.println(iCacheClient.get("hello"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void testCCSIN() {
		try {
			String ccs = mConfig.getProperty("CCSPARAM_INNER");
			ICCSComponent iCCSComponent = CCSComponentFactory.getConfigClient(ccs.split(",")[0], ccs.split(",")[1], ccs.split(",")[2]);
			if(!iCCSComponent.exists("/abc",ConfigPathMode.WRITABLE))
				iCCSComponent.add("/abc", "ok".getBytes());
			System.out.println(iCCSComponent.get("/abc",ConfigPathMode.WRITABLE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void testCCS() {
		try {
			String ccs = mConfig.getProperty("CCSPARAM");
			AuthDescriptor ad = new AuthDescriptor(AUTHURL, ccs.split(",")[0], ccs.split(",")[2],
					ccs.split(",")[1]);
			IConfigClient iConfigClient = ConfigFactory.getConfigClient(ad);
			if(!iConfigClient.exists("/abc"))
				iConfigClient.add("/abc", "ok".getBytes());
			System.out.println(iConfigClient.get("/abc"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
