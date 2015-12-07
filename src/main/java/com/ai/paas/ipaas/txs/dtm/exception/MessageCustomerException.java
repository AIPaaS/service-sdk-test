package com.ai.paas.ipaas.txs.dtm.exception;

public class MessageCustomerException extends LocalTransactionException {
	private static final long serialVersionUID = -1986959206315816260L;

	public MessageCustomerException(String message) {
		super(message);
	}

	public MessageCustomerException(String message, String errorcode) {
		super(message, errorcode);
	}
	
	public MessageCustomerException(String message, Exception e) {
		super(message, e);
	}
}
