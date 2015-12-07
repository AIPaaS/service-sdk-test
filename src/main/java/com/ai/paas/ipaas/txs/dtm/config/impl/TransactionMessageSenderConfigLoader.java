package com.ai.paas.ipaas.txs.dtm.config.impl;

import java.util.Properties;

import com.ai.paas.ipaas.txs.dtm.config.AuthLoader;
import com.ai.paas.ipaas.txs.dtm.config.ITransactionMessageSenderConfig;

/**
 * 事务性消息配置发送
 * 
 * @Title: TransactionMessageSenderConfigLoader.java 
 * @author wusheng
 * @date 2015年3月26日 上午11:18:51 
 *
 */
public class TransactionMessageSenderConfigLoader implements
		ITransactionMessageSenderConfig {
	private Properties transactionMessageKafkaProperties;
	
	@Override
	public Properties getTransactionMessageSenderKafkaProperties() {
		if(transactionMessageKafkaProperties == null){
			transactionMessageKafkaProperties = AuthLoader.getConfigInfo("dtm/local/message/kafka", Properties.class);
		}
		return transactionMessageKafkaProperties;
	}

}
