package test.com.ai.paas.ipaas.mds.messagesender.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ai.paas.ipaas.mds.IMessageSender;

import test.com.ai.paas.ipaas.mds.messagesender.base.MessageSender;

public class SendMsgStr2 extends MessageSender{
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
	   * 测试消息正常发送 String msg +key
	   * ***/
	  @Test(dataProvider="sendStringKey")
	  public void sendStringKey1(String msg,long pid,String key ){
		  int i=0;
		  while(i<20){
		  iMessageSender.send(msg, pid, key+i+pid);  //key+i
		  i++;
		  }
	  }
	  @DataProvider(name="sendStringKey")
	  public Object[][]sendStringKey(){
		  return new Object[][]{
				  {"testtherestring-str02",-2,"135200216873111"} //正常测试key ,String 的  msg; 
				  
		  };
	  }
	  /**
	   * 测试String msg + key,String为空的情况
	   * 
	   * **/
	  @Test(dataProvider="sendNullType2")
	  public void sendNullTypeTest3(String msg,long pid,String key){
		  try{
			  iMessageSender.send(msg,pid,key);
		  }catch(Exception e){
			  System.out.println(e.getMessage());
			  Assert.assertEquals("The message is null!", e.getMessage()); 
		  }
	  }
	  @DataProvider(name="sendNullType2")
	  public Object[][] sendNullType2(){
		return new Object[][]{
				{"",3,"13520021687"}
		};
		  
	  }   
}
