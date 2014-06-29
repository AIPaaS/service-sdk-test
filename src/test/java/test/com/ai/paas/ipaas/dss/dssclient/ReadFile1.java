package test.com.ai.paas.ipaas.dss.dssclient;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.ai.paas.ipaas.dss.interfaces.IDSSClient;

import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;
import test.com.ai.paas.ipaas.dss.dssclient.*;

public class ReadFile1 extends DSSClient{
  private IDSSClient  iDSSClient =null;
  @BeforeMethod
  public void BeforeMthod(){
	  try {
		iDSSClient  = super.getClient();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
  }
  /**
   * 测试传入的id值为空/""的情况  
   * */
  @Test(dataProvider="valueNull")  //passed
  public void readFile(String id) {
	  try{
	   iDSSClient.read(id);
	  }catch(Exception e){
		System.out.println( e.getMessage());
		 Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	  }
	  
  }
  @DataProvider(name="valueNull")
  public Object[][] valueNull(){     
	  return new Object[][]{
			  {null}, //  ERROR com.ai.paas.ipaas.dss.impl.DSSClient(137) - id illegal    DSS RUNTIME ERROR
			  {""}    //  ERROR com.ai.paas.ipaas.dss.impl.DSSClient(137) - id illegal    DSS RUNTIME ERROR
	  };
  }

 /***
  * 测试输入id值，查找不到返回NULL
  * */
  @Test(dataProvider="returnNull")  //passed
  public void readFile1(String id){
	 byte[] bytes = iDSSClient.read(id);
	 System.out.println(bytes);
	Assert.assertNull(bytes);
  }
  @DataProvider(name="returnNull")  
  public Object[][] returnNull(){   
	  return new Object[][]{
			  {"52a145a08c1cec10388cca99"}  
	  };
  }     
  /***
   * 测试正常读取      
   * */
  @Test(dataProvider="saveValue")  //passed
  public void readFile2(File file0,String remark){
	  String  id = iDSSClient.save(file0, remark);
	   System.out.println(id);
	   byte[] bytes = iDSSClient.read(id);
       System.out.println(bytes);
	   Assert.assertNotNull(bytes);
  }
 @DataProvider(name ="saveValue")  
  public Object[][] saveValue(){   
	return new Object[][]{  //4.00 KB
			{new java.io.File("./src/img/test7.txt"),"dsdsadsad"}     
			
	};
}
  
}
