package test.com.ai.paas.ipaas.dss.dssclient;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.ai.paas.ipaas.dss.interfaces.IDSSClient;

import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;


public class SaveFile2 extends DSSClient{
	private IDSSClient iDSSClient = null;
	
 @BeforeMethod
  public void beforeMethod() {
	 try{
	  iDSSClient  = super.getClient();
	 }catch(Exception e){
		System.out.println( e.getMessage());
	 }
  }
 
 /**
  * 验证Byte[]数组为空的情况，抛出的异常
  * */
  @Test(dataProvider="bytesnumber") //passed
  public void saveByte1(byte[] bytes,String remark) {
	  try{
		  String id= iDSSClient.save(bytes, remark);
		  Assert.assertNotNull(id);
		  System.out.println(id);
		
		   }catch(Exception e){
	    	 System.out.println(e);
	    	 Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage()); 
	    	 e.getStackTrace();
	     }
		  
		
  }
  @DataProvider(name="bytesnumber")
  public Object[][] bytesnumber(){
	return new Object[][]{ 
	         {null,"ppjhhmal"}, //报错    DSS RUNTIME ERROR
			 {"".getBytes(),"dsadsad"}    //不报错     
	       };
  }
  /*
   * 验证remark = null或""情况
   * **/
  @Test(dataProvider="remarkNullString")  //passed
  public void saveByte4(byte[] bytes,String remark){
	  try{
		  
		String id = iDSSClient.save(bytes, remark);
		System.out.println(id);
	  }catch(Exception e){
		  System.out.println(e.getMessage());
		  Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage()); 
	      e.getStackTrace();
	  }  
  }  
  
  @DataProvider(name="remarkNullString")
  public Object[][] remarkNullString(){
	   return  new  Object[][]{   
			     {"1223232323".getBytes(),null},  //为空 正常返回id,不报错
			     {"9876543211".getBytes(),""}   //""正常返回id,不报错      20
	   };
  }
  
  
  
  /**
   * 测试超过文件限制的情况，抛出异常 (4M)------------daice
   * */

@Test(dataProvider="bigBytes")   //passed  
public void saveByte2 (String remark){
	byte [] bytes1 = new  byte [5242880];   //4194304   4
	 try{ iDSSClient.save(bytes1,remark); 
		 }catch(Exception e){ 
			 System.out.println(e);
			 Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage()); 
	    	 e.getStackTrace();  
	 }
}
@DataProvider(name="bigBytes")
public Object[][] bigBytes(){
	 return new Object[][]{
			//{getBytesFromFile(new java.io.File("./src/img/test6.txt")),"dwedewdwedew"}
			 {"dwedewdwedew"}
	 };
}

  /**
   * 测试存贮文件超过了现有的空间报错   5M
   * */
 @Test(dataProvider="notEnough")   //passed
 public void saveByte3(String remark){
	  byte [] bytes =new byte[4194304] ; // 4
	 try{
		  iDSSClient.save(bytes,remark);
	 }catch(Exception e){
		System.out.println( e.getMessage());
		 Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage()); 
	 }
 }
 @DataProvider(name="notEnough")
 public Object[][] notEnough(){
		
	  return new Object[][]{ 
			//  {getBytesFromFile(new java.io.File("./src/img/test3.txt")),"maoioiomal"},  //test3.txt 换成更大的 8M
			    {"maoioiomal"}
            
     };
 }
/**
 * 正常测试  saveByte
 * */
 @Test(dataProvider="imgpath")  //passed
 public void saveByte(byte[] bytes,String remark){
	 try{

		  String id =  iDSSClient.save(bytes, remark);
		  Assert.assertNotNull(id);
		  System.out.println(id);
	 }catch(Exception e){
		 System.out.println(e.getMessage());
		 Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage()); 
	 }  
 }   
 @DataProvider(name="imgpath")  
   public Object[][] imgpath(){   
	 return new Object[][]{
			 {"fdsfssdddt".getBytes(),"fefefefefef"},
			 {"fsefsefdse".getBytes(),"zccsacsfdff"}  //20
			 
	 };
 }
}
