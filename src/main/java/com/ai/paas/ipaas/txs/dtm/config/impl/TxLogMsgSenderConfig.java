package com.ai.paas.ipaas.txs.dtm.config.impl;

import java.util.Properties;

import com.ai.paas.ipaas.txs.dtm.config.AuthLoader;
import com.ai.paas.ipaas.txs.dtm.config.ITxLogMsgSenderConfig;

/**
 * TX LOG发送配置
 * 
 * @Title: TxLogMsgSenderConfig.java 
 * @author wusheng
 * @date 2015年3月26日 上午11:18:32 
 *
 */
public class TxLogMsgSenderConfig implements ITxLogMsgSenderConfig {
	private Properties txLogMsgKafkaProperties;
	
	@Override
	public Properties getTxLogMsgKafkaProperties() {
		if(txLogMsgKafkaProperties == null){
			txLogMsgKafkaProperties = AuthLoader.getConfigInfo("dtm/local/txlog/kafka", Properties.class);
		}
		return txLogMsgKafkaProperties;
	}

}
