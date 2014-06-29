package test.com.ai.paas.ipaas.dss.dssclient;

import java.io.File;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.thread.*;
import test.com.ai.paas.ipaas.dss.dssclient.base.DSSClient;
import org.junit.*;
import com.ai.paas.ipaas.dss.interfaces.IDSSClient;


public class SaveFile1 extends DSSClient {
	private IDSSClient iDSSClient = null;
	
	
/**
 *   获得iDSSClient的对象   
 * */
  @BeforeMethod
  public void setUp() throws Exception {
	      try{
	      iDSSClient  =  super.getClient();  
	      }catch(Exception e){
	    	  System.out.println(  e.getMessage()+"dsadsdssd");
	    	  e.getStackTrace();
	      }       
		}
  /**
   * 正常测试 save方法 
 * @param arg1   
   * 
   * */    
  
  @Test(dataProvider ="imgPath")  
  public void saveTest1(File file0, String remark) { 
	  try{ 
			 String id =  iDSSClient.save(file0, remark);
			 Assert.assertNotNull(id);
			 System.out.println("&&&&&&&&"+id);
			 
		}catch(Exception e){   
		    System.out.println( e.getMessage());
		    System.out.println("0000"+ e.getMessage());
		    e.getStackTrace();
			Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
		
		}
 
 }    
  
 /***
 * 传的2个参数图片对象,string 的备注
 * */ 
@DataProvider(name ="imgPath")
  public Object[][] imgPath (){
	
	  return new Object[][]{ 
			  {new java.io.File("./src/img/test4.jpg"),"arkthenormal"},  //12.0 KB 
              {new java.io.File("./src/img/test5.jpg"), "eweeeeeeeeeh"}  //156 KB 
      };
 
   

  }   
 /**
  * 文件超过正常限定尺寸，验证抛出异常(4M)
  * */  
  @Test(dataProvider ="bigImgPath")
  public void saveFile2(File file1, String remark){
	  try{
	    String id = iDSSClient.save(file1, remark);  //passed
	    System.out.println(id);
	}catch(Exception e){
		  System.out.println(e.getMessage()+" 文件 超过限制");
	      Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage());
	  }
  }
  
  @DataProvider(name ="bigImgPath")
  public Object[][] bigimgpath (){   
	
	  return new Object[][]{ 
			  {new java.io.File("./src/img/test6.jpg"),"markkijimal"},  //4.53 MB
             
      };
 
     

  }
  /**
   * 测试remark为null和空串的情况，图片正确保存
   * */
  @Test(dataProvider="remarkNull")
  public void saveFile6(File file5,String remark){
	  try{  
		String id = iDSSClient.save(file5, remark);
		System.out.println(id);
		 Assert.assertNotNull(id);
	  }catch(Exception e){
		  System.out.println(e.getMessage());
		  Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage()); 
	  }  
  }
  @DataProvider(name ="remarkNull")
  public Object[][] remarkNull(){
	  return new Object [][]{
			  {new java.io.File("./src/img/test1.jpg"),null},   //不会报错         1.52 MB 
			  {new java.io.File("./src/img/test4.jpg"),""}        //不会报错    12.0 KB 
	  };
  }  
  /***
   * 测试存贮文件的空间不足，抛出异常 (5M)
   * */
  @Test(dataProvider="notEnough")
  public void saveFile3(File file2,String remark){
	  try{
		   iDSSClient.save(file2, remark);
	  }catch(Exception e){
		  System.out.println(e.getMessage()+"存贮磁盘空间不足");
		  Assert.assertEquals("DSS RUNTIME ERROR",e.getMessage()); 
	  }
  }
  @DataProvider(name ="notEnough")
  public Object[][] notEnough(){
		
	  return new Object[][]{ 
			  {new java.io.File("./src/img/test7.jpg"),"markkijimal"},  //test7.jpg 3.58 MB
             
      };
  }
  /**
   * 存的文件为null与"",测试抛出的异常类型
   * 
   * */
  @Test(dataProvider="inputNull")   
  public void saveFile4(File file3,String remark){
   try{  
		 iDSSClient.save(file3, remark);
		 
	  }catch(Exception e){
	
	          Assert.assertEquals(null,e.getMessage()); 
			  System.out.println(e.getMessage()); 
	  }
  }       
  @DataProvider(name ="inputNull")
  public Object[][] inputNull(){
		return new Object[][]{ 
	      {null,"markkijimal"},  //报的空指针
      };
  }  

}
