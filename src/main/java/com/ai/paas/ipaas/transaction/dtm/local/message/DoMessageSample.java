package com.ai.paas.ipaas.transaction.dtm.local.message;

import org.springframework.stereotype.Component;

import sdk.demo.util.ConfUtil;

import com.ai.paas.ipaas.ats.consumer.ServiceProviderSignature;

@Component
public class DoMessageSample implements IDoMessage{
	private String topic = ConfUtil.getProperty("ATSPARAM").split(",")[5];
	
	@ServiceProviderSignature(idFromAttribute="topic")
	@Override
	public void doThing(String arg1) {
		System.out.println("DoMessageSample-------"+arg1);
		if(arg1!=null)
			System.out.println("-----ATS  OK----");

		throw new RuntimeException("adfa");
	}

	
}
