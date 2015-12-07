package com.ai.paas.ipaas.ats.producer;


/**
 * 事务性消息工厂<br/>
 * 返回的消息发送者，会使用保障性事务一致性<br/>
 * 
 * @Title: TransactionMsgSenderFactory.java 
 * @author wusheng
 * @date 2015年3月24日 下午5:28:20 
 *
 */
public class TransactionMsgProducerFactory {
	/**
	 * 返回消息发送者
	 * 
	 * @param srvId
	 * @param ad
	 * @param topic
	 * @return
	 */
	public static TransactionMessageProducer getClient() {
		TransactionMessageProducer sender = new TransactionMessageProducer();
		return sender;
	}
}
