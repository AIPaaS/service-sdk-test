package com.ai.paas.ipaas.txs.dtm.config;

import java.util.Properties;

/**
 * 事务性消费者配置信息接口
 * 
 * @Title: ITransactionMessageCustomerConfig.java 
 * @author wusheng
 * @date 2015年3月27日 下午4:25:41 
 *
 */
public interface ITransactionMessageCustomerConfig {
	public Properties getTransactionMessageCustomerProperties();
	
	public boolean isTopicExist(String topic);
}
