package test.com.ai.paas.ipaas.dss.dssclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.ai.paas.ipaas.dss.interfaces.IDSSClient;

import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;

public class UpdateFile1 extends DSSClient {
	private IDSSClient iDSSClient =null;
  @BeforeClass
   public void beforeClass() throws Exception { 
	  
	     iDSSClient= super.getClient();   
	  
  } ;
  /**
   * 测试byte[]为null id为null/""的情况  
   * */
  @Test(dataProvider="updateNull")  //passed   
  public void updateTest(String id ,byte[] bytes) {
	  try{
	  iDSSClient.update(id, bytes);
	  }catch(Exception e){
		  System.out.println(e.getMessage());
	    	Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	  }
  }
  @DataProvider(name="updateNull")
  public Object[][] updateNull(){
	  return new Object[][]{
		     {null,"dsssdsd".getBytes()}, // ERROR com.ai.paas.ipaas.dss.impl.DSSClient(174) - id or bytes illegal     DSS RUNTIME ERROR
		     {"","dssddsd".getBytes()},   // ERROR com.ai.paas.ipaas.dss.impl.DSSClient(174) - id or bytes illegal     DSS RUNTIME ERROR
		     {null,null}                  //ERROR com.ai.paas.ipaas.dss.impl.DSSClient(174) - id or bytes illegal     DSS RUNTIME ERROR
	  }; 
  };      
    
  /**
   * 测试输入id(随意定的)与byte[]的值，此id找不到原有文件报错
   * **/
   @Test(dataProvider="updateMiss")  //passed  
   public void upateTest2(String id, byte[] bytes){
	   try{
	  iDSSClient.update(id, bytes); 
	  }catch(Exception e){
		 System.out.println(e.getMessage());
		 Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	  }
   }
   @DataProvider(name="updateMiss")   
   public Object[][] updateMiss(){ 
	   return new Object[][]{    
			   {"52a145a08c1cec10388cca99","124343dss".getBytes()},//ERROR com.ai.paas.ipaas.dss.impl.DSSClient(185) - file missing
			   {"53a145a08c1cec10388cca99","dsasaasxx".getBytes()} //ERROR com.ai.paas.ipaas.dss.impl.DSSClient(185) - file missing
	   };
   }
   /***
    * 测试正常的update   
    * */ 
   @Test(dataProvider="saveValue")  //passed
   public void updateTest3(File file1,String remark){   
	   
	String id  = iDSSClient.save(file1,remark);
	System.out.println(id+"4$44");
	 byte[] bytes1= remark.getBytes();
	 System.out.println(bytes1);
	 try{
	 iDSSClient.update(id, bytes1);
	   byte[] byte2 =iDSSClient.read(id);
	   System.out.println(byte2);
	   Assert.assertEquals(bytes1 , byte2);
	   
	   }catch(Exception e){
		 
		   System.out.println(  e.getMessage());
	   }
   }
   @DataProvider(name="saveValue")
	public Object[][] saveValue(){
		return new Object[][]{
				{new java.io.File("./src/img/test10.txt"),"fsffdsfdsf"}  //4.00 KB 
				
		};
	   
   }


/***超出了限制，报错
 * 测试超过文件限制,
 * */
@Test(dataProvider="fileSize")    //passed 
public void updateTest4(File file1,String remark){  
	String id =  iDSSClient.save(file1, remark);
	System.out.println(id);
	byte [] byte1 = new byte[5242880]; //4194304  4
   System.out.println(byte1);
	try{
		
	iDSSClient.update(id, byte1 );
	}catch(Exception e){
		System.out.println(e.getMessage());
		Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	}
}
@DataProvider(name="fileSize")
public Object[][] fileSize(){  
	return new Object[][]{
		{new java.io.File("./src/img/test11.txt"),"dededwefdefde" } //4.00 KB 
		
	
	} ;  
}
/***
 * 测试超出了空间 ，文件报错；  
 *        
 * */
@Test(dataProvider="dataSource")   //passed 
public void updateTest5(File file,String remark){
  String id = iDSSClient.save(file,remark);
  System.out.println(id);
   byte[]  bytes =new byte[4194304];//  文件要大(超过磁盘空间)
  try{
  iDSSClient.update(id,bytes );
  }catch(Exception e){
	  System.out.println( e.getMessage());
	  Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
  }
}      
@DataProvider(name="dataSource")  
public Object[][] dataSource(){
	return new Object[][]{
			{new java.io.File("./src/img/test9.txt"),"sdffesfsdfsd"}  //4.00 KB 
			
	};
	   
}  
}
