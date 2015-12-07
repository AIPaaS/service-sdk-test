package com.ai.paas.ipaas.txs.dtm.config.impl;

import com.ai.paas.ipaas.uac.vo.AuthDescriptor;


/**
 * 事务处理组件用户认证对外接口
 * 
 * @Title: IPaaSTransactionUserAuth.java 
 * @author wusheng
 * @date 2015年3月26日 上午11:30:57 
 *
 */
public interface IPaaSTransactionUserAuth {
	/**
	 * 获取用户鉴权信息
	 * @return
	 */
	public abstract AuthDescriptor getAuth();
	
	/**
	 * 获取认证地址
	 * @return
	 */
	public abstract String getAuthAddr();
	
	/**
	 * 获取用户名
	 * @return
	 */
	public abstract String getPid();
	
	/**
	 * 获取密码
	 * @return
	 */
	public abstract String getPassword();
	
	/**
	 * 获取事务服务号
	 * @return
	 */
	public abstract String getServiceId();
}
