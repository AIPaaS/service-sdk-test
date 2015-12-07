package sdk.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import sdk.demo.SDKTest;

public class ConfUtil {
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
	
	
	public static String getProperty(String key){
		return mConfig.getProperty(key);
	}
}
