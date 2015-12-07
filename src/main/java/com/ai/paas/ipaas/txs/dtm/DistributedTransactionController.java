package com.ai.paas.ipaas.txs.dtm;

import com.ai.paas.ipaas.txs.common.protocol.DTLProtocol;
import com.ai.paas.ipaas.txs.common.protocol.type.DT_STATUS;
import com.ai.paas.ipaas.txs.dtm.transfer.DtLogSender;

/**
 * 分布式事务控制器
 * 
 * @Title: DistributedTransactionController.java 
 * @author wusheng
 * @date 2015年3月18日 下午1:24:54 
 *
 */
public class DistributedTransactionController {
	/**
	 * 不需要实例化
	 */
	private DistributedTransactionController(){
		
	}
	
	public static void begin() {
		TransactionContext.initContext();
		
		DistributedTransactionContainer.init();
	}

	public static void commit() {
		//send tx log msg
		DTLProtocol protocol = TransactionContext.get();
		protocol.setDtStatus(DT_STATUS.COMMIT_LABEL);
		if(protocol.isDistributed()){
			DtLogSender.sendDTL(protocol);
		}
		
		
		DistributedTransactionContainer.commit();
	}

	public static void rollback() {
		/**
		 * 性能优化，rollback操作，不会发生事务不一致性，不用发送DT_STATUS.ROLLBACK_LABEL报文
		 */
		DistributedTransactionContainer.rollback();
	}

	public static void clear() {
		TransactionContext.clear();
		
		DistributedTransactionContainer.clear();
	}
}
