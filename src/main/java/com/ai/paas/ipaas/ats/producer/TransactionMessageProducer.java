package com.ai.paas.ipaas.ats.producer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.context.ApplicationContext;

import com.ai.paas.ipaas.ats.producer.util.MessagePartitionTransactionUtil;
import com.ai.paas.ipaas.txs.common.protocol.AppDTLProtocol;
import com.ai.paas.ipaas.txs.common.protocol.AppPartitionTransactionProtocol;
import com.ai.paas.ipaas.txs.common.protocol.PartitionTransactionConfirmProtocol;
import com.ai.paas.ipaas.txs.common.protocol.TransactionMessage;
import com.ai.paas.ipaas.txs.common.protocol.type.DT_STATUS;
import com.ai.paas.ipaas.txs.common.protocol.type.REDO_TYPE;
import com.ai.paas.ipaas.txs.dtm.DistributedTransactionContainer;
import com.ai.paas.ipaas.txs.dtm.TransactionContext;
import com.ai.paas.ipaas.txs.dtm.config.LocalConfigCenter;
import com.ai.paas.ipaas.txs.dtm.exception.LoadConfigException;
import com.google.gson.JsonObject;

/**
 * 事务管理器作用下的事务性消息<br/>
 * 
 * @Title: TransactionMessageSender.java
 * @author wusheng
 * @date 2015年3月24日 下午5:28:01
 *
 */
public class TransactionMessageProducer {
	private MessageTransactionController controller = null;
	
	private List<TransactionMessage> messages = new ArrayList<TransactionMessage>();
	
	private static Map<String, Boolean> TransactionSingature = new HashMap<String, Boolean>(); 
	
	TransactionMessageProducer() {
	}

	/**
	 * 存储发送上下文和启动事务管理器
	 */
	private void saveSendContext(TransactionMessage message) {
		//判断当前消息队列的合法性
		if(!TransactionSingature.containsKey(message.getTarget())){
			synchronized (TransactionSingature) {
				if(!TransactionSingature.containsKey(message.getTarget())){
					if (!LocalConfigCenter.instance().isTransactionMessageTopicExist(
							message.getTarget())) {
						throw new LoadConfigException("target singatureId:" + message.getTarget() + " is illegal.");
					}else{
						TransactionSingature.put(message.getTarget(), true);
					}
				}
			}
		}
		
		this.prepare();
		
		messages.add(message);
		
		AppDTLProtocol appDTLProtocol = TransactionContext.get()
				.getLocalAppDTL();
		AppPartitionTransactionProtocol appPartitionTransactionProtocol = appDTLProtocol
				.getPartitionTransaction(MessagePartitionTransactionUtil.getMessageTransactionSignature(this, messages.size()), "MSG");
		PartitionTransactionConfirmProtocol confirmProtocol = PartitionTransactionConfirmProtocol
				.getConfirmProtocol(appPartitionTransactionProtocol,
						DT_STATUS.COMMITTED);
		message.setConfirmProtocol(confirmProtocol.toJSON());
		
		if(message.isAutoResend()){
			appPartitionTransactionProtocol.setRedoType(REDO_TYPE.AUTO_REDO);
		}
		JsonObject logEntity = appPartitionTransactionProtocol.getInfo();
		logEntity.add("msg", message.toJSON());
		
		controller.addSender(this);
		
		/**
		 * 当前事务中存在事务性消息，则默认启动分布式事务控制
		 */
		appDTLProtocol.getParent().setDistributed(true);
	}

	private void prepare() {
		String key = MessageTransactionController.class.getName();
		controller = (MessageTransactionController) DistributedTransactionContainer
				.getController(key);
		if (controller == null) {
			controller = new MessageTransactionController();
			DistributedTransactionContainer.append(
					MessageTransactionController.class.getName(), controller);
		}
	}


	/**
	 * 发送事务性消息
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void sendMessage(TransactionMessage message) {
		this.saveSendContext(message);
	}

	List<TransactionMessage> getMessages() {
		return messages;
	}
}
