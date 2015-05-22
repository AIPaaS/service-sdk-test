package test.com.ai.paas.ipaas.dbs.distribute.distributedDataSource;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ai.paas.ipaas.dbs.distribute.DistributedDataSource;

public class GetConnectionTest {
	
	
	private DistributedDataSource dataSource;
	
	@Before
	public void setUp() {
		dataSource = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");  
		assertNotNull(dataSource);
		
		
	}
	
	@Test(expected = SQLException.class)
	public void  testGetConnection(){
		   Connection conn = null;
		   try{
			   conn =  dataSource.getConnection();
			   assertNotNull(conn);
		   }catch(SQLException e){
			   e.printStackTrace();
		   }
	}
	
	
	
	
   
	
	

}
