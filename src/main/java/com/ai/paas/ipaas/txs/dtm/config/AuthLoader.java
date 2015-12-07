package com.ai.paas.ipaas.txs.dtm.config;

import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.ccs.inner.CCSComponentFactory;
import com.ai.paas.ipaas.txs.dtm.config.impl.IPaaSTransactionUserAuth;
import com.ai.paas.ipaas.txs.dtm.exception.LoadConfigException;
import com.ai.paas.ipaas.uac.service.UserClientFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.paas.ipaas.uac.vo.AuthResult;
import com.google.gson.Gson;

/**
 * 获取当前的认证信息类
 * 
 * @Title: AuthLoader.java
 * @author wusheng
 * @date 2015年3月26日 上午11:27:45
 *
 */
public class AuthLoader {
	private final static String TRANSACTION_CONFIG_PATH = "/TXS/";

	private static IPaaSTransactionUserAuth authLoader;

	private static AuthResult authResult;

	private static Object lock = new Object();

	public static void init(IPaaSTransactionUserAuth loader) {
		authLoader = loader;
	}

	public static IPaaSTransactionUserAuth getAuthLoader() {
		if (authResult == null) {
			synchronized (lock) {
				if (authResult == null) {
					if (authLoader == null) {
						throw new LoadConfigException(
								"IPaaS transaction user auth is not define.");
					}
					AuthDescriptor auth = authLoader.getAuth();
					authResult = UserClientFactory.getUserClient().auth(
							auth);
				}
			}
		}
		return authLoader;
	}

	/**
	 * 获取指定类型的配置信息
	 * 
	 * @param subPath
	 * @param type
	 * @return
	 */
	public static <T> T getConfigInfo(String subPath, Class<T> type) {
		String serviceId = getAuthLoader().getServiceId();
		String msgConf;
		try {
			msgConf = CCSComponentFactory.getConfigClient(
					authResult.getConfigAddr(), authResult.getConfigUser(),
					authResult.getConfigPasswd()).get(
					TRANSACTION_CONFIG_PATH + serviceId + "/" + subPath);
		} catch (ConfigException e) {
			throw new LoadConfigException(e.getMessage(), e);
		}
		Gson gson = new Gson();
		return gson.fromJson(msgConf, type);
	}

	/**
	 * 配置中心制定路径是否存在
	 * 
	 * @param subPath
	 * @return
	 */
	public static boolean existedPath(String subPath) {
		String serviceId = getAuthLoader().getServiceId();
		try {
			return CCSComponentFactory.getConfigClient(authResult.getConfigAddr(),
					authResult.getConfigUser(), authResult.getConfigPasswd())
					.exists(TRANSACTION_CONFIG_PATH + serviceId + "/" + subPath);
		} catch (ConfigException e) {
			throw new LoadConfigException(e.getMessage(), e);
		}
	}

	/**
	 * 获取认证结果
	 * 
	 * @return
	 */
	public static AuthResult getAuthResult() {
		return authResult;
	}
}
