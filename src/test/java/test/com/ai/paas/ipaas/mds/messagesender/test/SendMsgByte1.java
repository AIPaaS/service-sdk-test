package test.com.ai.paas.ipaas.mds.messagesender.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ai.paas.ipaas.mds.IMessageSender;

import test.com.ai.paas.ipaas.mds.messagesender.base.MessageSender;

public class SendMsgByte1 extends MessageSender  {
	  private IMessageSender   iMessageSender;
	  @BeforeClass
		public void beforeClass() {
		       try {
		   iMessageSender=super.getClient();
			} catch (Exception e) {
				e.printStackTrace();  
			}
		 } 
	 /**
	   * 测试消息正常发送；byte[] bytes;
	   * */
	  @Test(dataProvider="sendByte")  
	  public void sendByte(byte[] bytes,long arg1){
		  int i =0;
		  while(i<20){
		  iMessageSender.send(bytes, arg1); //id+i
		  i++;
		  };
		
	  }
	  @DataProvider(name="sendByte")    
	  public Object[][]sendByte(){
		  return  new Object[][]{
				  {"testonestring-str02".getBytes(),-15}  //正常测试Byte
		  };
	  }  
	  /***
	   * 测试byte[]为空的情况     
	   * **/
	  @Test(dataProvider="sendNullType1")
	  public void sendNullTypeTest1(byte[] msg,long pid){
		  try{
		  iMessageSender.send(msg, pid);
		  }catch(Exception e){
			  System.out.println(e.getMessage());
			  Assert.assertEquals("The message is null!", e.getMessage()); 
		  }
	  }  
	  @DataProvider(name="sendNullType1")
	  public Object[][] sendNullType1(){
		  return new Object[][]{
				  {"",3}
		  };
	  }
}
