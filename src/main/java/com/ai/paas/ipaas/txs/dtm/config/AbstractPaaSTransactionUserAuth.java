package com.ai.paas.ipaas.txs.dtm.config;

import com.ai.paas.ipaas.txs.dtm.config.impl.IPaaSTransactionUserAuth;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

/**
 * 事务处理组件用户认证对外接口
 * 
 * @Title: AbstractPaaSTransactionUserAuth.java 
 * @author wusheng
 * @date 2015年3月27日 下午1:48:43 
 *
 */
public abstract class AbstractPaaSTransactionUserAuth implements IPaaSTransactionUserAuth {
	/**
	 * 获取用户鉴权信息
	 * @return
	 */
	public AuthDescriptor getAuth(){
		AuthDescriptor auth = new AuthDescriptor();
		auth.setAuthAdress(this.getAuthAddr());
		auth.setPid(this.getPid());
		auth.setPassword(this.getPassword());
		auth.setServiceId(this.getServiceId());
		return auth;
	}

}
