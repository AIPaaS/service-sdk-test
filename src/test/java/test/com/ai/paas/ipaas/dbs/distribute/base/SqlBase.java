package test.com.ai.paas.ipaas.dbs.distribute.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;

import com.ai.paas.ipaas.dbs.distribute.DistributedDataSource;
import com.ai.paas.ipaas.dbs.sequence.ISequenceClient;
import com.ai.paas.ipaas.dbs.sequence.SequenceFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class SqlBase {
	
private DistributedDataSource dataSource;

private ISequenceClient sequenceClient;


private static String userName = "zh_ka@163.com";

private static String  password = "123456";

private static String  serviceId = "DBS001";

private static String   authAddr = "http://10.1.228.198:14821/iPaas-Auth/service/check";

	@Before
	public void setUp() {
		dataSource = new DistributedDataSource(userName,password,serviceId,authAddr);  
		AuthDescriptor auth = new AuthDescriptor();
		auth.setAuthAdress(authAddr);
		auth.setPassword(password);
		auth.setServiceId(serviceId);
		auth.setUserName(userName);
		sequenceClient = SequenceFactory.getClient(auth);
		
}
	
	
	public  DistributedDataSource  getDataSource() {
		
		return dataSource;
		
	}
	
	public ISequenceClient  getSequenceClient() {
		
		return sequenceClient;
	}
	
	
	protected void close(ResultSet resultSet,Statement statement,Connection conn) {
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
