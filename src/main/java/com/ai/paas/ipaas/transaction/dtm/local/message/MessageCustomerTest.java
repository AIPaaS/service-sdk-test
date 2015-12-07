package com.ai.paas.ipaas.transaction.dtm.local.message;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

import sdk.demo.util.ConfUtil;

import com.ai.paas.ipaas.ats.consumer.TransactionMsgCustomerFactory;
import com.ai.paas.ipaas.sample.configImpl.PaaSTransactionUserAuth;

public class MessageCustomerTest {
	//private static ApplicationContext context = null;

	static {
//		context = new ClassPathXmlApplicationContext(new String[] {
//				"/context/applicationContext.xml"});
		PaaSTransactionUserAuth auth = new PaaSTransactionUserAuth();
		auth.init();
	}
	
	public void testMessage(){
		TransactionMsgCustomerFactory.register(IDoMessage.class, new DoMessageSample());
		TransactionMsgCustomerFactory.start(ConfUtil.getProperty("ATSPARAM").split(",")[5]);
		
		while(true){
			try {
				Thread.sleep(10000000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
