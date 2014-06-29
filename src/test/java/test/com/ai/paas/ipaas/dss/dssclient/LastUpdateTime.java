package test.com.ai.paas.ipaas.dss.dssclient;

import java.io.File;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import com.ai.paas.ipaas.dss.interfaces.IDSSClient;

import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;

public class LastUpdateTime extends DSSClient{
	 private IDSSClient iDSSClient = null;
	 @BeforeClass
	  public void beforeClass() {  
	      try{
		      iDSSClient  =  super.getClient();  
		      }catch(Exception e){
		    	  System.out.println( e.getMessage()+"dsadsdssd");
		    	  e.getStackTrace();
		      }   
	  }
   /***
    *  测试输入的id值为null,验证异常；
    * */
   @Test(dataProvider = "valueNull")
  public void lastUpdate(String id) {     //passed 
       try{
	    	iDSSClient.getLastUpdateTime(id);
	    }catch(Exception e){   
	        System.out.println(	e.getMessage());
	    	Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	    }
   }       

  @DataProvider
  public Object[][] valueNull() {
    return new Object[][] {
    		{null}    // ERROR com.ai.paas.ipaas.dss.impl.DSSClient(211) - id is null    
    };                // DSS RUNTIME ERROR
  }
  /**
   * 测试输入id值，找不到对应的id值，抛出异常
   * */
  @Test(dataProvider="idCode")   //passed  
  public void updateDate1(String id){
	  try{
	  iDSSClient.getLastUpdateTime(id);
	  }catch(Exception e){
			
			System.out.println(e.getMessage());
	    	Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	  }
  }
  @DataProvider(name="idCode")
  public Object[][] idCode(){     
	  return new Object[][]{
			  {"52a145a08c1cec10388cca99"}  //ERROR com.ai.paas.ipaas.dss.impl.DSSClient(217) - file missing  
	  };
  }
  /**
   * 测试输入id正常返回Date
   * **/
  @Test(dataProvider="idValue")     //passed
  public void updateDate2(File file1,String remark){
	  String  id  = iDSSClient.save(file1, remark);  
	  System.out.println(id);
	  Date  date1 = iDSSClient.getLastUpdateTime(id);
	  System.out.println(date1);
	  Assert.assertNotNull(date1);
  } 
  @DataProvider(name="idValue")    
  public Object[][] idValue(){
	  return new Object[][]{  
			  {new java.io.File("./src/img/test9.txt"),"sfdsfdsfds"}   //返回DATE数值   4KB
	  };
  }
     
}
