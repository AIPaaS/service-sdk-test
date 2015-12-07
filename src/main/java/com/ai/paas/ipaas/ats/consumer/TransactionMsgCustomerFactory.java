package com.ai.paas.ipaas.ats.consumer;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.ai.paas.ipaas.mds.IMessageConsumer;
import com.ai.paas.ipaas.mds.MsgConsumerFactory;
import com.ai.paas.ipaas.mds.impl.consumer.client.Config;
import com.ai.paas.ipaas.txs.dtm.config.AuthLoader;
import com.ai.paas.ipaas.txs.dtm.config.LocalConfigCenter;
import com.ai.paas.ipaas.txs.dtm.exception.LoadConfigException;
import com.ai.paas.ipaas.txs.dtm.exception.MessageCustomerException;

/**
 * 事务性消息消费端
 * 
 * @Title: TransactionMsgCustomerFactory.java
 * @author wusheng
 * @date 2015年3月27日 下午4:10:02
 *
 */
public class TransactionMsgCustomerFactory {
	private static Map<String, Object> CUSTOMER_INSTANCE = new ConcurrentHashMap<String, Object>();
	
	public static final String ERROR_ATS_CHANNEL_PREFIX = "error-";

	public static void register(Class<?> interfaceClass, Object instance) {
		CUSTOMER_INSTANCE.put(interfaceClass.getName(), instance);
	}

	static Object getHandlerInstance(String interfaceClassName) {
		if (CUSTOMER_INSTANCE.containsKey(interfaceClassName)) {
			return CUSTOMER_INSTANCE.get(interfaceClassName);
		} else {
			throw new MessageCustomerException("interface:"
					+ interfaceClassName + " is not registered.");
		}
	}

	/**
	 * 启动新的线程开始并行处理事务消息
	 * 
	 * @param singatureId
	 *            消息主题（在服务申请时提供）
	 * @param msgProcessorHandlerPrototype
	 *            处理类原型，实例化为每个分区创建一个对象实例
	 */
	public static void start(String singatureId) {
		if (!LocalConfigCenter.instance().isTransactionMessageTopicExist(
				singatureId)) {
			throw new LoadConfigException("singatureId:" + singatureId + " is illegal.");
		}
		Properties props = LocalConfigCenter.instance()
				.getTransactionMessageCustomerProperties();
		props.put(Config.MDS_USER_SRV_ID, AuthLoader.getAuthLoader().getServiceId());
		IMessageConsumer customer = MsgConsumerFactory.getClient(
				AuthLoader.getAuthResult(), props, singatureId,
				new TransactionMsgProcessorHandler(singatureId, true));
		customer.start();
		
		//error ats channel
		customer = MsgConsumerFactory.getClient(
				AuthLoader.getAuthResult(), props, ERROR_ATS_CHANNEL_PREFIX + singatureId,
				new TransactionMsgProcessorHandler(singatureId, false));
		customer.start();
	}
}
