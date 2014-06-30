package test.com.ai.paas.ipaas.mds.messagesender.test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.ai.paas.ipaas.mds.IMessageSender;




import test.com.ai.paas.ipaas.mds.messagesender.base.MessageSender;


public class SendMsgStr1 extends MessageSender  {
 private IMessageSender iMessageSender  ;
  @BeforeClass
	public void beforeClass() {
	       try {
	   iMessageSender=super.getClient();
		} catch (Exception e) {
			e.printStackTrace();  
		}
	 } 
  /**   
   * 测试消息正常发送；String msg
   * **/
  @Test(dataProvider="sendStr")
  public void sendStr1(String msg,long pid) {   // msg 为消息   pid为分区id;
		int i=0;
		while(i<20){
	   iMessageSender.send(msg+i, pid);
	   i++;
	   //消息+i
		} 
  }  
   
  @DataProvider(name="sendStr")
  public Object[][] senderStr(){   
	  return new Object[][]{
			 {"testonestring-str04",4}, // 正常测试String   
			             };  
  }
 
  /***
   * 测试String msg 为空的情况
   * **/
  @Test(dataProvider="sendNullType")
  public void sendNullTypeTest(String msg,long pid){
	 try{
	   iMessageSender.send(msg, pid);
	 }catch(Exception e){
		  System.out.println(e.getStackTrace());
		  System.out.println(e.getMessage());
	      Assert.assertEquals("The message is null!", e.getMessage());  //msg传空会报空指针异常；
	 }
}
  @DataProvider(name="sendNullType")
  public Object[][] sendErrorType(){
	return new Object[][]{
			{"",4},
			{null,4}
		
		
	};
	  
  }
  /*
   * 测试一条数据
   * **/
  @Test(dataProvider="sendStr1")
  public void sendStr2(String msg,long pid) {   // msg 为消息   pid为分区id;
	 
	
	   iMessageSender.send(msg, pid); //消息+i
	  
  }  
   
  @DataProvider(name="sendStr1")
  public Object[][] senderStr1(){   
	  return new Object[][]{
			 {"testonestring-str02",4}, // 正常测试String   
			             };  
  }

}
