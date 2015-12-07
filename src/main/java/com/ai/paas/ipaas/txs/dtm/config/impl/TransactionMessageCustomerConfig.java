package com.ai.paas.ipaas.txs.dtm.config.impl;

import java.util.Properties;

import com.ai.paas.ipaas.txs.dtm.config.AuthLoader;
import com.ai.paas.ipaas.txs.dtm.config.ITransactionMessageCustomerConfig;

public class TransactionMessageCustomerConfig implements
		ITransactionMessageCustomerConfig {
	private Properties transactionMessageCustomerProperties;

	@Override
	public Properties getTransactionMessageCustomerProperties() {
		if(transactionMessageCustomerProperties == null){
			transactionMessageCustomerProperties = AuthLoader.getConfigInfo("dtm/local/message-customer/kafka", Properties.class);
		}
		return transactionMessageCustomerProperties;
	}

	@Override
	public boolean isTopicExist(String topic) {
		return AuthLoader.existedPath("dtm/local/message-customer/topic/" + topic);
	}

}
