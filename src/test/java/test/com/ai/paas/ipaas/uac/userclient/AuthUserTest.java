package test.com.ai.paas.ipaas.uac.userclient;

import org.junit.Before;
import org.junit.Test;

import test.com.ai.paas.ipaas.uac.userclient.base.UserClient;

import com.ai.paas.ipaas.uac.service.IUserClient;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class AuthUserTest extends UserClient {
	private IUserClient iUserClient = null;
	private AuthDescriptor ad = null;
	private String authAddr = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private String srvId = "CCS001";
	private String authUser = "mvne@asiainfo.com";
	private String authPasswd = "mvne";

	@Before
	public void setUp() throws Exception {
		iUserClient = super.getClient();
	}

	/*** 正常情况测试 */
	@Test
	public void authUser() {
		ad = new AuthDescriptor(authAddr, authUser, authPasswd, srvId);
		iUserClient.authUser(ad);
	}

	/*** null测试 */
	@Test(expected = IllegalArgumentException.class)
	public void authUserParamNull() {
		iUserClient.authUser(null);
	}

	/*** authAddr对象字段为null */
	@Test(expected = IllegalArgumentException.class)
	public void authAddrNull() {
		ad = new AuthDescriptor(null, authUser, authPasswd, srvId);
		iUserClient.authUser(ad);
	}

	/*** authAddr对象字段为空字符串 */
	@Test(expected = IllegalArgumentException.class)
	public void authAddrBlank() {
		ad = new AuthDescriptor("", authUser, authPasswd, srvId);
		iUserClient.authUser(ad);
	}

	/*** authUser对象字段为null */
	@Test(expected = IllegalArgumentException.class)
	public void authUserNull() {
		ad = new AuthDescriptor(authAddr, null, authPasswd, srvId);
		iUserClient.authUser(ad);
	}

	/*** authUser对象字段为空字符串 */
	@Test(expected = IllegalArgumentException.class)
	public void authUserBlank() {
		ad = new AuthDescriptor(authAddr, "", authPasswd, srvId);
		iUserClient.authUser(ad);
	}

	/*** authPasswd对象字段为null */
	@Test(expected = IllegalArgumentException.class)
	public void authPasswdNull() {
		ad = new AuthDescriptor(authAddr, authUser, null, srvId);
		iUserClient.authUser(ad);
	}

	/*** authUser对象字段为空字符串 */
	@Test(expected = IllegalArgumentException.class)
	public void authPasswdBlank() {
		ad = new AuthDescriptor(authAddr, authUser, "", srvId);
		iUserClient.authUser(ad);
	}

	/*** srvId对象字段为null */
	@Test(expected = IllegalArgumentException.class)
	public void srvIdNull() {
		ad = new AuthDescriptor(authAddr, authUser, authPasswd, null);
		iUserClient.authUser(ad);
	}

	/*** srvId对象字段为空字符串 */
	@Test(expected = IllegalArgumentException.class)
	public void srvIdBlank() {
		ad = new AuthDescriptor(authAddr, authUser, authPasswd, "");
		iUserClient.authUser(ad);
	}

}
