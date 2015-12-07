package com.ai.paas.ipaas.txs.dtm.exception;

import com.ai.paas.ipaas.txs.common.exception.TransactionException;

/**
 * 读取配置数据异常
 * 
 * @Title: LoadConfigException.java 
 * @author wusheng
 * @date 2015年3月20日 上午9:57:37 
 *
 */
public class LoadConfigException extends TransactionException {
	private static final long serialVersionUID = 1095900863807468631L;

	public LoadConfigException(String message) {
		super(message);
	}

	public LoadConfigException(String message, String errorcode) {
		super(message, errorcode);
	}
	
	public LoadConfigException(String message, Exception e) {
		super(message, e);
	}
}
