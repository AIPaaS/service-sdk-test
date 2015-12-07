package com.ai.paas.ipaas.ats.consumer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.paas.ipaas.PaaSConstant;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.paas.ipaas.txs.common.protocol.PartitionTransactionConfirmProtocol;
import com.ai.paas.ipaas.txs.common.protocol.TransactionMessage;
import com.ai.paas.ipaas.txs.common.protocol.type.DT_STATUS;
import com.ai.paas.ipaas.txs.common.util.TimeUtil;
import com.ai.paas.ipaas.txs.dtm.config.LocalConfigCenter;
import com.ai.paas.ipaas.txs.dtm.exception.MessageBusinessCustomerException;
import com.ai.paas.ipaas.txs.dtm.exception.MessageCustomerException;
import com.ai.paas.ipaas.txs.dtm.transfer.DtLogSender;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * 事务性消息默认处理
 * 
 * @Title: TransactionMsgProcessor.java
 * @author wusheng
 * @date 2015年3月29日 下午7:28:25
 *
 */
public class TransactionMsgProcessor implements IMessageProcessor {
	private static Logger logger = LogManager
			.getLogger(TransactionMsgProcessor.class);

	private static Map<String, String> LEGAL_TRANSACTION_PROCESSOR = new ConcurrentHashMap<String, String>();

	private String topicId;

	private boolean isMainMsgProcessor;

	public TransactionMsgProcessor(String topicId, boolean isMainMsgProcessor) {
		super();
		this.topicId = topicId;
		this.isMainMsgProcessor = isMainMsgProcessor;
	}

	/**
	 * 处理事务性消息<br/>
	 * 通过获取消息的TransactionMessage<br/>
	 * 反射执行消息对应的类方法，完成后通过confirm报文，向DTM.center进行上报<br/>
	 * 
	 */
	@Override
	public void process(MessageAndMetadata msgAndMetadata) throws Exception {
		TransactionMessage message = null;
		PartitionTransactionConfirmProtocol confirmProtocol = null;
		try {
			String msgJSON = new String(msgAndMetadata.getMessage(),
					PaaSConstant.CHARSET_UTF8);
			if (logger.isDebugEnabled()) {
				logger.debug("processing transaction message.message="
						+ msgJSON);
			}
			message = (TransactionMessage) new TransactionMessage()
					.getInstance(new Gson().fromJson(msgJSON, JsonObject.class));
			confirmProtocol = (PartitionTransactionConfirmProtocol) new PartitionTransactionConfirmProtocol()
					.getInstance(message.getConfirmProtocol());
		} catch (Exception e) {
			logger.warn("illegal message. ignore.", e);
			return;
		}

		try {
			String serviceProviderName = message.getTargetClassName();
			Object serviceProviderObject = TransactionMsgCustomerFactory
					.getHandlerInstance(serviceProviderName);
			Class<?> serviceProviderClass = serviceProviderObject.getClass();
			int argNum = message.getArgs().size();
			Class<?>[] argTypes = new Class<?>[argNum];
			if (argNum > 0) {
				for (int i = 0; i < argNum; i++) {
					argTypes[i] = message.getArgs().get(i).getClass();
				}
			}
			String methodName = message.getTargetMethodName();
			String targetMethodFullName = serviceProviderName + "."
					+ methodName;
			Method targetMethod = serviceProviderClass.getDeclaredMethod(
					methodName, argTypes);

			if (targetMethod == null) {
				throw new MessageCustomerException("serviceProviderClass:"
						+ serviceProviderName + " methodName:" + methodName
						+ " not found");
			}

			if (!LEGAL_TRANSACTION_PROCESSOR.containsKey(targetMethodFullName)) {
				ServiceProviderSignature annotation = targetMethod
						.getAnnotation(ServiceProviderSignature.class);
				if (annotation == null) {
					throw new MessageCustomerException("serviceProviderClass:"
							+ serviceProviderName + " methodName:" + methodName
							+ " is not signature!");
				} else {
					String signatrueId = annotation.id();

					if (!topicId.equals(signatrueId)) {
						if (signatrueId.length() > 0) {
							throw new MessageCustomerException(
									"serviceProviderClass:"
											+ serviceProviderName
											+ " methodName:"
											+ methodName
											+ " signature to "
											+ signatrueId
											+ " , is signature to other topic! illegal message.");
						}
						// not match topic (signatrueId is empty), try to get
						// field
						String signatrueIdAttr = annotation.idFromAttribute();

						if (signatrueIdAttr.length() == 0) {
							throw new MessageCustomerException(
									"serviceProviderClass:"
											+ serviceProviderName
											+ " methodName:" + methodName
											+ " is signature to empty topic.");
						}
						Field field = null;
						try {
							field = serviceProviderClass
									.getDeclaredField(signatrueIdAttr);
						} catch (Exception e) {
							// ignore, continue try
						}
						if (field == null) {
							try {
								field = serviceProviderClass
										.getField(signatrueIdAttr);
							} catch (Exception e) {
							}
						}
						if (field == null) {
							throw new MessageCustomerException(
									"serviceProviderClass:"
											+ serviceProviderName
											+ " methodName:"
											+ methodName
											+ " is signature to no existed field");
						}
						field.setAccessible(true);

						Object fieldValue = field.get(serviceProviderObject);
						String realTopic = null;
						if (fieldValue instanceof String) {
							realTopic = (String) fieldValue;
						} else {
							throw new MessageCustomerException(
									"serviceProviderClass:"
											+ serviceProviderName
											+ " methodName:" + methodName
											+ " is signature to field:"
											+ signatrueIdAttr
											+ " , which is not String type.");
						}

						if (!topicId.equals(realTopic)) {
							throw new MessageCustomerException(
									"serviceProviderClass:"
											+ serviceProviderName
											+ " methodName:"
											+ methodName
											+ " is signature to other topic! illegal message");
						}
					}

					LEGAL_TRANSACTION_PROCESSOR.put(targetMethodFullName,
							targetMethodFullName);
				}
			}

			try {
				targetMethod.invoke(serviceProviderObject, message.getArgs()
						.toArray());
			} catch (Exception e) {
				/**
				 * 业务处理异常，当前消息队列分区阻塞，会触发重复调用
				 * 
				 * 1.如果是正常队列，则转发到异常队列中 
				 * 
				 * 2.如果是异常队列，则重发
				 */
				if (isMainMsgProcessor) {
					Properties kafaProps = LocalConfigCenter.instance().getTransactionMessageSenderKafkaProperties();
					IMessageSender msgSender = MsgSenderFactory.getClient(kafaProps, null, TransactionMsgCustomerFactory.ERROR_ATS_CHANNEL_PREFIX + message.getTarget());
					msgSender.send(message.toJSON().toString(), message.getChannelId());
				} else {
					throw new MessageBusinessCustomerException(
							"business process error.message=" + e.getMessage(),
							e);
				}
			}

			confirmProtocol.setDtStatus(DT_STATUS.COMMITTED);
			confirmProtocol.setConfirmTime(TimeUtil.getTimestamp());
			DtLogSender
					.sendPartitionTransactionConfirmProtocol(confirmProtocol);
		} catch (JsonSyntaxException e) {
			logger.warn("illegal message. ignore.", e);

			/**
			 * 主动忽略的异常，主动发送confirm
			 */
			DtLogSender
					.sendPartitionTransactionConfirmProtocol(confirmProtocol);
		} catch (MessageCustomerException e) {
			logger.warn("illegal message. ignore.", e);

			/**
			 * 主动忽略的异常，主动发送confirm
			 */
			DtLogSender
					.sendPartitionTransactionConfirmProtocol(confirmProtocol);
		}
	}

}
