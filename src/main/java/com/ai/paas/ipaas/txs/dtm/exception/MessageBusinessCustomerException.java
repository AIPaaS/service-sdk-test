package com.ai.paas.ipaas.txs.dtm.exception;

/**
 * 消息业务性消费操作异常<br/>
 * 
 * @Title: MessageBusinessCustomerException.java 
 * @author wusheng
 * @date 2015年3月31日 下午12:11:11 
 *
 */
public class MessageBusinessCustomerException extends LocalTransactionException {
	private static final long serialVersionUID = 312683961074396219L;

	public MessageBusinessCustomerException(String message) {
		super(message);
	}

	public MessageBusinessCustomerException(String message, String errorcode) {
		super(message, errorcode);
	}
	
	public MessageBusinessCustomerException(String message, Exception e) {
		super(message, e);
	}
}
