package com.ai.paas.ipaas.sample.configImpl;

import sdk.demo.util.ConfUtil;

import com.ai.paas.ipaas.txs.dtm.config.AbstractPaaSTransactionUserAuth;
import com.ai.paas.ipaas.txs.dtm.config.AuthLoader;
import com.ai.paas.ipaas.uac.service.UserClientFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.ai.paas.ipaas.uac.vo.AuthResult;

/**
 * 示例权限配置的实现类
 * 
 * @Title: PaaSTransactionUserAuth.java
 * @author wusheng
 * @date 2015年3月26日 下午2:14:18
 *
 */
public class PaaSTransactionUserAuth extends AbstractPaaSTransactionUserAuth {
	public void init() {
		AuthLoader.init(this);
	}

	@Override
	public String getServiceId() {
		return ConfUtil.getProperty("ATSPARAM").split(",")[3];
	}

	@Override
	public String getAuthAddr() {
		return ConfUtil.getProperty("AUTHURL");
	}

	@Override
	public String getPid() {
		return ConfUtil.getProperty("ATSPARAM").split(",")[0];
	}

	@Override
	public String getPassword() {
		return ConfUtil.getProperty("ATSPARAM").split(",")[4];
	}

	public static void main(String[] args) {
		AuthDescriptor auth = new AuthDescriptor(
				"http://10.1.228.198:14821/iPaas-Auth/service/auth",
				"sunhs3@asiainfo.com", "wfATSService", "TXS001");
		AuthResult authResult = UserClientFactory.getUserClient()
				.auth(auth);
		System.out.println(authResult.getUserName());
	}
}
