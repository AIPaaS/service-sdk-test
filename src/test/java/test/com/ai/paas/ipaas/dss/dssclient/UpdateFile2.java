package test.com.ai.paas.ipaas.dss.dssclient;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.ai.paas.ipaas.dss.interfaces.IDSSClient;

import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;

public class UpdateFile2 extends DSSClient{ 
	private IDSSClient  iDSSClient =null;
 @BeforeClass
   public void beforeClass() throws Exception {
	  iDSSClient = super.getClient();
	  }
 /***
  * 测试输入的id 值与输入的文件为空的情况
  * */   
  @Test(dataProvider="updateNull")  //passed
  public void updateTest(String id,File file) {
	  try{
	  iDSSClient.update(id, file); 
	  }catch(Exception e){
		  System.out.println(e.getMessage());
	    	Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	  } 
  } 
  @DataProvider(name="updateNull")
  public Object[][] updateNull(){
	  return new Object[][]{
			  {null,null},
			  {"",null}
	  }; 
  }; 
  /**
   * 测试输入id(随意定的)与file，此id找不到原有文件报错
   * **/
   @Test(dataProvider="updateMiss1")                 //passed 
   public void upateTest2(String id,File file1){
	   try{
	  iDSSClient.update(id, file1);
	  
	  }catch(Exception e){
		 System.out.println(e.getMessage());
		 Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	  }   
   }
   @DataProvider(name="updateMiss1") 
   public Object[][] updateMiss1(){ 
	   return new Object[][]{    
			   {"53a145a08c1cec10388cca99",new java.io.File("./src/img/test4.jpg")},
			   {"52a145a08c1cec10388cca99",new java.io.File("./src/img/test5.jpg")}
	   };  
   }
   
   /***
    * 测试正常的update---
    * */
   @Test(dataProvider="saveValue")   //passed
   public void updateTest3(File file1,String remark){
	try{
	String id  = iDSSClient.save(file1,remark);
	System.out.println(id+"%%%");
	File  file2 = new java.io.File("./src/img/test6.txt");  //4.00 KB 
    iDSSClient.update(id,file2);
     Assert.assertNotEquals(file1, file2);
     }catch(Exception e){
    	 System.out.println(e.getMessage());
     }
	
   }   
   @DataProvider(name="saveValue")
	public Object[][] saveValue(){  
		return new Object[][]{  
				{new java.io.File("./src/img/test5.txt"),"fsffdsfdsf"}  //4.00 KB 
				
		};
	   
   }
   /***超出了限制，报错   
    * 测试超过文件限制,  4M
    * */
   @Test(dataProvider="fileSize")
   public void updateTest4(File file,String remark){  
   	String id =  iDSSClient.save(file, remark);
   	File file1 = new java.io.File("./src/img/test6.jpg");  //4.53 MB
   	try{
   	iDSSClient.update(id, file1);
   	}catch(Exception e){
   		System.out.println(e.getMessage());
   		Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
   	}
   }
   @DataProvider(name="fileSize")
   public Object[][] fileSize(){
   	return new Object[][]{
   			{new java.io.File("./src/img/test5.jpg"),"dededwefdefdewdedd" } //  156 KB
   	
   	} ;
   }
   /***
    * 测试超出了空间 ，文件报错；  
    *        
    * */
   @Test(dataProvider="dataSource")  //PASSED
   public void updateTest5(File file,String remark){
     String id = iDSSClient.save(file,remark); 
     File file1 = new java.io.File("./src/img/test7.jpg");//  文件要大(超过磁盘空间)  //  3.58 MB
     try{
     iDSSClient.update(id,file1 );
     }catch(Exception e){
   	  System.out.println( e.getMessage());
   	  Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
     } 
   }       
   @DataProvider(name="dataSource")  
   public Object[][] dataSource(){  
   	return new Object[][]{
   			{new java.io.File("./src/img/test9.txt"),"sdffesfsdfsd"}  //4.00 KB ;
   			
   	};     
   	
   }   
}   
