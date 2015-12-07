package com.ai.paas.ipaas.txs.dtm.config;

import java.util.Properties;

/**
 * 读取分布式事务底层消息发送机制的配置信息
 * 
 * @Title: ITransactionMessageSenderConfig.java 
 * @author wusheng
 * @date 2015年3月25日 下午1:35:30 
 *
 */
public interface ITransactionMessageSenderConfig {
	public Properties getTransactionMessageSenderKafkaProperties();
}
