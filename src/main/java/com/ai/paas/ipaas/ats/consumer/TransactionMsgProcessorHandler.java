package com.ai.paas.ipaas.ats.consumer;

import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;

/**
 * 事务性消息默认的消息处理器
 * 
 * @Title: TransactionMsgProcessorHandler.java 
 * @author wusheng
 * @date 2015年3月29日 下午7:25:18 
 *
 */
public class TransactionMsgProcessorHandler implements IMsgProcessorHandler {
	private String topicId;
	
	private boolean isMainMsgProcessor;
	
	public TransactionMsgProcessorHandler(String topicId, boolean isMainMsgProcessor) {
		super();
		this.topicId = topicId;
		this.isMainMsgProcessor = isMainMsgProcessor;
	}

	@Override
	public IMessageProcessor[] createInstances(int partition) {
		IMessageProcessor[] instanceArray = new IMessageProcessor[partition];
		for(int i = 0; i < partition; i++){
			instanceArray[i] = new TransactionMsgProcessor(topicId, isMainMsgProcessor);
		}
		return instanceArray;
	}

}
