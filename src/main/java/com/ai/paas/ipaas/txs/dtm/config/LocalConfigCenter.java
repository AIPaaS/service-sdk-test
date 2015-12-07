package com.ai.paas.ipaas.txs.dtm.config;

import java.util.Properties;

import com.ai.paas.ipaas.txs.dtm.config.impl.TransactionMessageCustomerConfig;
import com.ai.paas.ipaas.txs.dtm.config.impl.TransactionMessageSenderConfigLoader;
import com.ai.paas.ipaas.txs.dtm.config.impl.TxLogMsgSenderConfig;

/**
 * 内部配置依赖中心，用于读取相关配置信息
 * 
 * @Title: LocalConfigCenter.java 
 * @author wusheng
 * @date 2015年3月25日 下午1:36:16 
 *
 */
public class LocalConfigCenter {
	private static LocalConfigCenter center = new LocalConfigCenter();
	
	private ITxLogMsgSenderConfig txLogMsgSenderConfig = new TxLogMsgSenderConfig();
	
	private ITransactionMessageSenderConfig transactionMessageSenderConfig = new TransactionMessageSenderConfigLoader();
	
	private ITransactionMessageCustomerConfig transactionMessageCustomerConfig = new TransactionMessageCustomerConfig();
	
	public static LocalConfigCenter instance(){
		return center;
	}
	
	public Properties getTxLogMsgKafkaProperties(){
		return txLogMsgSenderConfig.getTxLogMsgKafkaProperties();
	}
	
	public Properties getTransactionMessageSenderKafkaProperties(){
		return transactionMessageSenderConfig.getTransactionMessageSenderKafkaProperties();
	}
	
	public Properties getTransactionMessageCustomerProperties(){
		return transactionMessageCustomerConfig.getTransactionMessageCustomerProperties();
	}
	
	public boolean isTransactionMessageTopicExist(String topic){
		return transactionMessageCustomerConfig.isTopicExist(topic);
	}
}
