package com.ai.paas.ipaas.txs.dtm.exception;

import com.ai.paas.ipaas.txs.common.exception.TransactionException;

/**
 * 本地分布式事务消息发送异常
 * 
 * @Title: LocalTransactionTransferException.java 
 * @author wusheng
 * @date 2015年3月18日 下午4:40:02 
 *
 */
public class LocalTransactionTransferException extends TransactionException {
	private static final long serialVersionUID = 4507371611025900417L;

	public LocalTransactionTransferException(String message) {
		super(message);
	}

	public LocalTransactionTransferException(String message, String errorcode) {
		super(message, errorcode);
	}
	
	public LocalTransactionTransferException(String message, Exception e) {
		super(message, e);
	}
}
