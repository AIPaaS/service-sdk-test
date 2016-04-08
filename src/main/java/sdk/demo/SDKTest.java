package sdk.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import sdk.demo.util.MessageCustomerTask;
import sdk.demo.util.MsgProcessorHandlerImpl;

import com.ai.paas.ipaas.ccs.ConfigFactory;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.inner.CCSComponentFactory;
import com.ai.paas.ipaas.ccs.inner.ICCSComponent;
import com.ai.paas.ipaas.ccs.inner.constants.ConfigPathMode;
import com.ai.paas.ipaas.dss.DSSFactory;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;
import com.ai.paas.ipaas.image.IImageClient;
import com.ai.paas.ipaas.image.ImageClientFactory;
import com.ai.paas.ipaas.mcs.CacheFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;
import com.ai.paas.ipaas.mds.MsgConsumerFactory;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.search.service.ISearchClient;
import com.ai.paas.ipaas.search.service.SearchClientFactory;
import com.ai.paas.ipaas.transaction.dtm.local.message.MessageTest;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.paas.ipaas.util.StringUtil;

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
		
		System.out.println("testMDS");
		testMDS();
		
		System.out.println("testATS");
		testATS();
		
		System.out.println("testSES");
		testSES();
	}
	
	
	private static void testMDS() {
			String mds = mConfig.getProperty("MDSPARAM");
			if(StringUtil.isBlank(mds)) {
				System.out.println("MDS Not configed, skipped!");
				return;
			}
			AuthDescriptor ad = new AuthDescriptor(AUTHURL, mds.split(",")[0], mds.split(",")[2],
					mds.split(",")[1]);
			IMessageSender msgSender = null;
			System.out.println("MDS SENDER BEGIN ++++++++++++++++" +mds);
			try {
				msgSender = MsgSenderFactory.getClient(ad, mds.split(",")[3]);
			} catch (Exception e) {
				System.out.println("MDS COMSUMER ERROR");
				e.printStackTrace();
			}
			msgSender
			.send("adsajddddddddddsadsadsa"
					+ "dddddddddddddddddddasdsadsadsadd"
					+ "ddddddddddddddddddddddddasdsadsadsadsdasdsadddddddddddddddddddddasdsadsadsadsd"
					+ "dfgfdgggggdgfdgdfgfdgfdgfdgfdgfdgfdgdddddddddddddddddddddasdsadsadsadsd"
					+ "dfsdfdsferytertertretrretretedddddddddddddddddddddasdsadsadsadsd"
					+ "fsdfdsfdsfdsfsdfsdfsfsdfsdfsddddddddddddddddddddddasdsadsadsadsd"
					+ "fdsfsdfsfdsfdsfsdfdsfsdfsdfsddddddddddddddddddddddasdsadsadsadsd"
					+ "fdsfsfsdfdsfsdtryrtyryryrtyrytrdddddddddddddddddddddasdsadsadsadsd"
					+ "retetertretretretetretretertertedddddddddddddddddddddasdsadsadsadsd"
					+ "retertertetretertrefdgdgdfgdddddddddddddddddddddasdsadsadsadsd"
					+ "dgdfgggggggggggggggggggggfdgdfgdfdddddddddddddddddddddasdsadsadsadsd"
					+ "gdfggggggggggggggggggggggggggggfhdddddddddddddddddddddasdsadsadsadsd"
					+ "hgwllllllllllllllllllllllllldddddddddddddddddddddasdsadsadsadsd"
					+ "asdddddddddddddddddddddddddddddddddddddddddasdsadsadsadsd"
					+ "kkkkkkkkkkkkkkkkkkkkkdsa", 0);
			msgSender.send("Byte message01".getBytes(), 1);
			msgSender.send("Byte message02".getBytes(), 2);
			msgSender.send("Byte message03".getBytes(), 3);
			msgSender.send("Byte message04".getBytes(), 4);
			msgSender.send("Byte message05".getBytes(), 5);
			msgSender.send("Byte message06".getBytes(), 6);
			msgSender.send("Byte message07".getBytes(), 7);
			msgSender.send("Byte message08".getBytes(), 8);
			msgSender.send("Byte message09".getBytes(), 9);
			System.out.println("MDS SENDER SUCCESS");
			System.out.println("MDS COMSUMER TEST BEGIN");
			IMsgProcessorHandler msgProcessorHandler = new MsgProcessorHandlerImpl();
			IMessageConsumer msgConsumer = null;
			msgConsumer = MsgConsumerFactory.getClient(ad, mds.split(",")[3],
					msgProcessorHandler);
			msgConsumer.start();
			int i=0;
			while (i<100) {
				try {
					Thread.sleep(10000);
					i++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("MDS COMSUMER TEST SUCCESS");

		
	}
	
	
	private static void testSES() {
		try {
			String mcs = mConfig.getProperty("SESPARAM");
			if(StringUtil.isBlank(mcs)) {
				System.out.println("MCS Not configed, skipped!");
				return;
			}
			AuthDescriptor ad = new AuthDescriptor(AUTHURL, mcs.split(",")[0], mcs.split(",")[2],
					mcs.split(",")[1]);
			
			
			ISearchClient iSearchClient = null;
				iSearchClient = SearchClientFactory.getSearchClient(ad);
			iSearchClient.insertData("{\"abc\":\"test\"}");
//			System.out.println(iSearchClient.);
			System.out.println("testSES ok");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void testATS() {
		//MessageCustomerTest
		try {
			String ats = mConfig.getProperty("ATSPARAM");
			if(StringUtil.isBlank(ats)) {
				System.out.println("ATS Not configed, skipped!");
				return;
			}
			Thread mcust = new Thread(new MessageCustomerTask());
			mcust.start();
			MessageTest mt = new MessageTest();
			System.out.println("testATS--send message！");
			mt.testSendMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void testDSS() {
		try {
			String dss = mConfig.getProperty("DSSPARAM");
			if(StringUtil.isBlank(dss)) {
				System.out.println("DSS Not configed, skipped!");
				return;
			}
			AuthDescriptor ad = new AuthDescriptor(AUTHURL, dss.split(",")[0], dss.split(",")[2],
					dss.split(",")[1]);
			IDSSClient iDSSClient = DSSFactory.getClient(ad);
			String id = iDSSClient.save("ok".getBytes(), "test");
			System.out.println("id:"+id);
			System.out.println(iDSSClient.read(id));
			System.out.println(new String(iDSSClient.read(id)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	private static void testMCS() {
		try {
			String mcs = mConfig.getProperty("MCSPARAM");
			if(StringUtil.isBlank(mcs)) {
				System.out.println("MCS Not configed, skipped!");
				return;
			}
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
			if(StringUtil.isBlank(ccs)) {
				System.out.println("CCS IN Not configed, skipped!");
				return;
			}
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
			if(StringUtil.isBlank(ccs)) {
				System.out.println("CCS  Not configed, skipped!");
				return;
			}
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
	
	private static void testIDPS(){
		try{
			String ccs = mConfig.getProperty("IDPSPARAM");
			if(StringUtil.isBlank(ccs)) {
				System.out.println("IDPS  Not configed, skipped!");
				return;
			}
			AuthDescriptor ad = new AuthDescriptor(AUTHURL, ccs.split(",")[0], ccs.split(",")[2],
					ccs.split(",")[1]);
			IImageClient im = ImageClientFactory.getSearchClient(ad);
			Class config_class = SDKTest.class;
			InputStream inStream = new FileInputStream(new File(config_class.getResource("/config/test.jpg").toURI()));
			byte[] buff= new byte[100];
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			int rc = 0;  
	        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
			String imageId = im.upLoadImage(swapStream.toByteArray(), ".jpg");
			
			System.out.println(imageId);
		}catch (Exception e){
			
			e.printStackTrace();
		}
	}
}
