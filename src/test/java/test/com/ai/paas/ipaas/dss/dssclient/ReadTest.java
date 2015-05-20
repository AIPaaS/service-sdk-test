package test.com.ai.paas.ipaas.dss.dssclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;

import com.ai.paas.ipaas.dss.DSSFactory;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;

import org.junit.Before;
import org.junit.Test;

public class ReadTest extends DSSClient {
	private IDSSClient iDSSClient = null;

	@Before
	public void setUp() throws Exception {
		iDSSClient = super.getClient();
	}

	/*** 正常情况测试 
	 * @throws FileNotFoundException */
	@Test
	public void read() {
		try {
			byte[] fileByte = iDSSClient.read("555af67539434c28d050916a");
			File file = new File("C://Users//CYM//Desktop//test//test.jpg");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(fileByte);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*** null测试 */
	//TODO
	@Test(expected = Exception.class)
	public void readFirstNull() {
		String str0 = null;
		iDSSClient.read(str0);
	}

	/*** 空对象 */
	@Test(expected = Exception.class)
	public void readFirstBlank() {
		String str0 = "";
		iDSSClient.read(str0);
	}

}
