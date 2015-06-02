package test.com.ai.paas.ipaas.ccs.ccscomponent.base;

import com.ai.paas.ipaas.ccs.constants.ConfigCenterConstants;
import com.ai.paas.ipaas.ccs.inner.CCSComponentFactory;
import com.ai.paas.ipaas.ccs.inner.ICCSComponent;
import com.ai.paas.ipaas.util.CiperUtil;
import com.ai.paas.ipaas.util.UUIDTool;


public class CCSComponent {
	
	protected final String configAddr = "127.0.0.1:2181";
	protected String userName = UUIDTool.genId32();
	protected int userPwd = UUIDTool.genShortId();
	protected String adminName = "admin";
	protected String adminPwd = CiperUtil.decrypt(ConfigCenterConstants.operators, "ec4c9e0e78f76a69");
	private ICCSComponent iCCSComponent = null;

	public ICCSComponent getClient() throws Exception {
		iCCSComponent = CCSComponentFactory.getConfigClient(configAddr, userName, CiperUtil.encrypt(ConfigCenterConstants.operators, String.valueOf(userPwd)));
		return iCCSComponent;
	}
}
