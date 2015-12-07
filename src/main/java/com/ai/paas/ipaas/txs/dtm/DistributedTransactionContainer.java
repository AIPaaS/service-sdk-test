package com.ai.paas.ipaas.txs.dtm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.paas.ipaas.txs.dtm.controller.IPartitionTransactionController;
import com.ai.paas.ipaas.txs.dtm.exception.LocalTransactionException;

/**
 * 外部分布式事务处理容器
 * 
 * @Title: DistributedTransactionContainer.java
 * @author wusheng
 * @date 2015年3月17日 下午1:52:44
 *
 */
public class DistributedTransactionContainer {
	private static Logger logger = LogManager
			.getLogger(DistributedTransactionContainer.class);

	private static ThreadLocal<HashMap<String, IPartitionTransactionController>> CONTAINER = new ThreadLocal<HashMap<String, IPartitionTransactionController>>();

	static void init() {
		HashMap<String, IPartitionTransactionController> controllers = CONTAINER
				.get();
		if (controllers == null) {
			controllers = new HashMap<String, IPartitionTransactionController>();
			CONTAINER.set(controllers);
		} else {
			throw new LocalTransactionException(
					"DistributedTransactionContainer init error.");
		}
	}

	/**
	 * 在当前线程上下文中，增加自定义的事务控制器
	 * 
	 * @param key
	 * @param controller
	 * @return
	 */
	public static boolean append(String key,
			IPartitionTransactionController controller) {
		HashMap<String, IPartitionTransactionController> controllers = CONTAINER
				.get();
		if (controllers == null) {
			throw new LocalTransactionException(
					"DistributedTransactionContainer not init correctly.");
		}
		if (controllers.containsKey(key)) {
			return false;
		}
		controllers.put(key, controller);
		return true;
	}

	/**
	 * 返回当前上下文中，指定的事务控制器
	 * 
	 * @param key
	 * @return
	 */
	public static IPartitionTransactionController getController(String key) {
		HashMap<String, IPartitionTransactionController> controllers = CONTAINER
				.get();
		if (controllers == null) {
			return null;
		}
		if (controllers.containsKey(key)) {
			return controllers.get(key);
		} else {
			return null;
		}
	}

	static void commit() {
		HashMap<String, IPartitionTransactionController> controllers = CONTAINER
				.get();
		if (controllers != null) {
			//优先提交高优先级的事务，目前高优先级为JDBC事务
			List<IPartitionTransactionController> lowPriorityControllerList = new ArrayList<IPartitionTransactionController>();
			for (IPartitionTransactionController controller : controllers
					.values()) {
				try {
					if (controller.isHighPriority()) {
						controller.commit();
					} else {
						lowPriorityControllerList.add(controller);
					}
				} catch (Throwable e) {
					logger.warn("commit failure.", e);
				}
			}
			
			//后置提交低优先级事务，目前为发送消息
			if (lowPriorityControllerList.size() > 0) {
				for (IPartitionTransactionController controller : lowPriorityControllerList) {
					try {
						controller.commit();
					} catch (Throwable e) {
						logger.warn("commit failure.", e);
					}
				}
			}
		} else {
			throw new LocalTransactionException(
					"DistributedTransactionContainer not init correctly.");
		}
	}

	static void rollback() {
		HashMap<String, IPartitionTransactionController> controllers = CONTAINER
				.get();
		if (controllers != null) {
			for (IPartitionTransactionController controller : controllers
					.values()) {
				try {
					controller.rollback();
				} catch (Throwable e) {
					logger.warn("rollback failure.", e);
				}
			}
		} else {
			throw new LocalTransactionException(
					"DistributedTransactionContainer not init correctly.");
		}
	}

	static void clear() {
		HashMap<String, IPartitionTransactionController> controllers = CONTAINER
				.get();
		CONTAINER.set(null);
		if (controllers != null) {
			for (IPartitionTransactionController controller : controllers
					.values()) {
				try {
					controller.clear();
				} catch (Throwable e) {

				}
			}
		}
	}
}
