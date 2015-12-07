package com.ai.paas.ipaas.transaction.dtm.local.message;

import org.springframework.stereotype.Component;

import com.ai.paas.ipaas.ats.consumer.ServiceProviderSignature;

@Component
public class DoMessageSample implements IDoMessage{
	
	@ServiceProviderSignature(id="signatureId-52928f9a-ba92-4bfc-b370-8f082a4d08e9", idFromAttribute="TOPIC")
	@Override
	public void doThing(String arg1) {
		System.out.println("DoMessageSample-------"+arg1);
		if(arg1!=null)
			System.out.println("-----ATS  OK----");

		throw new RuntimeException("adfa");
	}

	
}
