package com.ai.paas.ipaas.txs.dtm;

import com.ai.paas.ipaas.txs.common.protocol.DTLProtocol;
import com.ai.paas.ipaas.txs.common.protocol.type.DT_STATUS;
import com.ai.paas.ipaas.txs.common.util.TimeUtil;
import com.ai.paas.ipaas.txs.dtm.config.AuthLoader;
import com.ai.paas.ipaas.txs.dtm.exception.LocalTransactionException;
import com.ai.paas.ipaas.util.UUIDTool;

/**
 * 事务上下文对象<br/>
 * 保存当前上下文信息<br/>
 * 
 * @Title: TransactionContext.java 
 * @author wusheng
 * @date 2015年3月13日 下午5:03:48 
 *
 */
public class TransactionContext {
	private static ThreadLocal<DTLProtocol> CONTEXT = new ThreadLocal<DTLProtocol>();
	
	/**
	 * 新建虚拟事务的上下文
	 * @return
	 */
	public static DTLProtocol initVisualContext(){
		DTLProtocol protocol = CONTEXT.get();
		if(protocol != null){
			throw new LocalTransactionException("Distributed Transaction Log has been init.");
		}
		protocol = new DTLProtocol();
		protocol.setTXID("DTL-" + UUIDTool.genId32());
		protocol.setStartTime(TimeUtil.getTimestamp());
		protocol.setDtStatus(DT_STATUS.RUNNING);
		protocol.setPaasUserId("");
		CONTEXT.set(protocol);
		return protocol;
	}
	
	/**
	 * 新建事务的上下文
	 * @return
	 */
	public static DTLProtocol initContext(){
		DTLProtocol protocol = CONTEXT.get();
		if(protocol != null){
			throw new LocalTransactionException("Distributed Transaction Log has been init.");
		}
		protocol = new DTLProtocol();
		protocol.setTXID("DTL-" + UUIDTool.genId32());
		protocol.setStartTime(TimeUtil.getTimestamp());
		protocol.setDtStatus(DT_STATUS.RUNNING);
		protocol.setPaasUserId(AuthLoader.getAuthLoader().getPid());
		CONTEXT.set(protocol);
		return protocol;
	}
	
	/**
	 * 获取当前的事务上下文<br/>
	 * 
	 * @return
	 */
	public static DTLProtocol get(){
		DTLProtocol protocol = CONTEXT.get();
		if(protocol == null){
			throw new LocalTransactionException("Distributed Transaction Log not init.");
		}
		return protocol;
	}
	
	/**
	 * 尝试获取当前的事务上下文，和injectContext配合使用，用于Protocol的上下文传递
	 * @return
	 */
	public static DTLProtocol getExistedProtocol(){
		return CONTEXT.get();
	}
	
	/**
	 * 注入上下文对象，允许从外部（主要是其他线程）注入上下文，保持整体上下文一致性
	 * 
	 * @param protocol
	 */
	public static void injectContext(DTLProtocol protocol){
		CONTEXT.set(protocol);
	}
	
	/**
	 * 清除当前上下文<br/>
	 * 
	 * @return
	 */
	public static void clear(){
		CONTEXT.remove();
	}
}
