package com.ai.paas.ipaas.txs.dtm.exception;

import com.ai.paas.ipaas.txs.common.exception.TransactionException;

/**
 * 本地事务异常<br/>
 * 
 * @Title: LocalTransactionException.java 
 * @author wusheng
 * @date 2015年3月13日 下午4:58:49 
 *
 */
public class LocalTransactionException extends TransactionException {
	private static final long serialVersionUID = -5290449620581350495L;

	public LocalTransactionException(String message) {
		super(message);
	}

	public LocalTransactionException(String message, String errorcode) {
		super(message, errorcode);
	}
	
	public LocalTransactionException(String message, Exception e) {
		super(message, e);
	}
}
