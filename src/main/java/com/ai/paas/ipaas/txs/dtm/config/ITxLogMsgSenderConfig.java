package com.ai.paas.ipaas.txs.dtm.config;

import java.util.Properties;

/**
 * 读取TX LOG的发送信息
 * @Title: ITxLogMsgSenderConfig.java 
 * @author wusheng
 * @date 2015年3月20日 上午9:59:58 
 *
 */
public interface ITxLogMsgSenderConfig {
	/**
	 * 获取消息发送的配置信息
	 * @return
	 */
	public Properties getTxLogMsgKafkaProperties();
}
