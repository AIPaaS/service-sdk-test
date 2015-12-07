package com.ai.paas.ipaas.txs.dtm.controller;

import com.ai.paas.ipaas.txs.common.protocol.DTLProtocol;
import com.ai.paas.ipaas.txs.dtm.TransactionContext;

/**
 * 受TXS管控的线程
 * 
 * @author wusheng
 *
 */
public abstract class TxsThread extends Thread {
	private DTLProtocol dtlProtocol;
	
	public TxsThread(ThreadType type){
		dtlProtocol = TransactionContext.getExistedProtocol();
	}
	
	@Override
	public final void run(){
		try {
			TransactionContext.injectContext(dtlProtocol);
			this.doRun();
		} finally{
			TransactionContext.clear();
		}
	}
	
	/**
	 * 受TXS管控的线程运行方法
	 */
	public abstract void doRun();
	
	public enum ThreadType{
		REQUIRED_PARENT
	}
}
