package com.ai.paas.ipaas.ats.producer;

import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.paas.ipaas.ats.producer.util.MessagePartitionTransactionUtil;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.txs.common.protocol.AppDTLProtocol;
import com.ai.paas.ipaas.txs.common.protocol.AppPartitionTransactionProtocol;
import com.ai.paas.ipaas.txs.common.protocol.PartitionTransactionConfirmProtocol;
import com.ai.paas.ipaas.txs.common.protocol.TransactionMessage;
import com.ai.paas.ipaas.txs.common.protocol.type.DT_STATUS;
import com.ai.paas.ipaas.txs.dtm.TransactionContext;
import com.ai.paas.ipaas.txs.dtm.config.LocalConfigCenter;
import com.ai.paas.ipaas.txs.dtm.controller.IPartitionTransactionController;
import com.ai.paas.ipaas.txs.dtm.transfer.DtLogSender;

/**
 * 消息事务控制器<br/>
 * 延时发送消息，保证事务的一致性，并支持事务保障机制<br/>
 * 
 * @Title: MessageTransactionController.java 
 * @author wusheng
 * @date 2015年3月24日 下午8:06:25 
 *
 */
public class MessageTransactionController implements
		IPartitionTransactionController {
	private static Logger logger = LogManager.getLogger(MessageTransactionController.class);
	
	private Set<TransactionMessageProducer> senders = new LinkedHashSet<TransactionMessageProducer>();
	
	void addSender(TransactionMessageProducer senderInstance){
		if(!senders.contains(senderInstance)){
			senders.add(senderInstance);
		}
	}

	@Override
	public void commit() throws Exception {
		Properties kafaProps = LocalConfigCenter.instance().getTransactionMessageSenderKafkaProperties();
		
		AppDTLProtocol appDTLProtocol = TransactionContext.get()
				.getLocalAppDTL();
		
		for(TransactionMessageProducer sender : senders){
			int index = 1;
			for(TransactionMessage message : sender.getMessages()){
				try{
					IMessageSender msgSender = MsgSenderFactory.getClient(kafaProps, null, message.getTarget());
					msgSender.send(message.toJSON().toString(), message.getChannelId());
					
					/**
					 * 发送当前消息的发送确认标记
					 */
					AppPartitionTransactionProtocol appPartitionTransactionProtocol = appDTLProtocol
							.getPartitionTransaction(MessagePartitionTransactionUtil.getMessageTransactionSignature(sender, index), "MSG");
					PartitionTransactionConfirmProtocol confirmProtocol = PartitionTransactionConfirmProtocol
							.getConfirmProtocol(appPartitionTransactionProtocol,
									DT_STATUS.COMMIT_LABEL);
					
					DtLogSender
							.sendPartitionTransactionConfirmProtocol(confirmProtocol);
				}catch(Exception e){
					//消息发送异常可以根据依赖后台机制重发，则此异常时，只输出异常日志，不抛异常
					logger.error("Transaction Message send error. message=" + message, e);
				}finally{
					index++;
				}
			}
		}
	}

	@Override
	public void rollback() throws Exception {
		
	}

	@Override
	public void clear() throws Exception {
		
	}

	@Override
	public boolean isHighPriority() {
		return false;
	}

}
