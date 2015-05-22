package test.com.ai.paas.ipaas.dbs.base;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.ai.paas.ipaas.dbs.config.UserAuth;
import com.ai.paas.ipaas.uac.vo.AuthResult;


public class AuthTest extends TestCase{
	
	private static final Logger logger = Logger.getLogger(AuthTest.class);
	
	public static void main(String[] args) {
		
	}
	
	
	public void testAuth() {
		
		UserAuth userAuth = new UserAuth("zh_ka@163.com","123456","DBS001","http://10.1.228.198:14821/iPaas-Auth/service/check");
		if(userAuth.authCheck()){
			logger.info("测试正常");
		}
		AuthResult authResult = userAuth.getAuthResult();
		if(authResult != null){
			
			System.out.println("ConfigAddr === " + authResult.getConfigAddr());
			System.out.println("ConfigPasswd ===" + authResult.getConfigPasswd());
			System.out.println("ConfigUser ===" + authResult.getConfigUser());
			System.out.println("UserId ===" + authResult.getUserName());
			
			
		}
		
	}

}
