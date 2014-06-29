package test.com.ai.paas.ipaas.dss.dssclient;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.ai.paas.ipaas.dss.interfaces.IDSSClient;

import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;

public class DeleteFile extends DSSClient { 
private IDSSClient iDSSClient =null;
  @BeforeMethod
  public void beforeMethod() { 
	    try {
			iDSSClient  = super.getClient();
		} catch (Exception e) {
			System.out.println(e.getMessage());
	        e.printStackTrace();
		}
	  
	  }
 /***   
  * 测试传入id的值为null与""抛出异常
  * */
  @Test(dataProvider="idValue")   //passed
  public void deteleFile1(String id) {   
	    try{ iDSSClient.delete(id);     
	    	
	    }catch(Exception e){
	    	System.out.println(e.getMessage());  
	    	e.getStackTrace();  
			Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	    }
  }
  
  @DataProvider(name="idValue")
  public Object[][] idValue(){
	  return new Object[][]{
			  {null},   //ERROR com.ai.paas.ipaas.dss.impl.DSSClient(157) - id illegal   DSS RUNTIME ERROR
			  {""}      //ERROR com.ai.paas.ipaas.dss.impl.DSSClient(157) - id illegal   DSS RUNTIME ERROR  
	  };   
  }
  /***
   * 输入id值，查找不到此id值对应的文件，返回false值，
   * */
  @Test(dataProvider="falseId")   //passed
  public void deleteFile2(String id){   
	 System.out.println(iDSSClient.delete(id));//返回false
	 Assert.assertFalse(iDSSClient.delete(id));
  }
  
  @DataProvider(name="falseId")
  public Object[][] falseId (){   
	  return new Object[][]{
			  {"52a145a08c1cec10388cca99" }  
			  
	  };
  }    
  /**
   * 找到文件，删除文件
   * **/
  @Test(dataProvider="removeFile") //passed 
    public void deleteFile3(File file2,String rmewak){
       String id = iDSSClient.save(file2, rmewak);
       System.out.println(id);  
       Assert.assertTrue( iDSSClient.delete(id));  
	    System.out.println(id);   
	//  System.out.println(iDSSClient.delete(id)); 
	       
       } 
  @DataProvider(name="removeFile")   
  public Object[][] removeFile(){    
	  return new Object[][]{  
			  {new java.io.File("./src/img/test8.txt"),"3243dadd"} //4kb
	  };
  }; 
  
  
} 
