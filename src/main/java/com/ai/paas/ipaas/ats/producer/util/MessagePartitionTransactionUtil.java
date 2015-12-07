package com.ai.paas.ipaas.ats.producer.util;

import com.ai.paas.ipaas.ats.producer.TransactionMessageProducer;

/**
 * 消息子事务工具类
 * 
 * @Title: MessagePartitionTransactionUtil.java 
 * @author wusheng
 * @date 2015年3月25日 上午10:32:32 
 *
 */
public class MessagePartitionTransactionUtil {
	public static String getMessageTransactionSignature(TransactionMessageProducer sender, int index) {
		return "tx=" + sender.toString() + "." + index;
	}
}
