package test.com.ai.paas.ipaas.dbs.distribute.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ai.paas.ipaas.dbs.distribute.DistributedDataSource;

public class DistribtedConnectionTest {
	
	
	private static final String URL = "http://10.1.228.198:14821/iPaas-Auth/service/check";
	private static final String USER_NAME = "zh_ka@163.com";
	private static final String PASSWORD = "123456";
	private static final String SERVICE_ID = "DBS001";
	
	private  DistributedDataSource ds;
	
	private  Connection conn;
	
	private  PreparedStatement ps;
	
	@Before
	public void setup() throws Exception {
		
		ds = new DistributedDataSource(USER_NAME,PASSWORD,SERVICE_ID,URL);
		
		conn = ds.getConnection();
	}
	
	@Test
	public void   prepareSqlIsNull() throws Exception {
		String sql = null;
		ps = conn.prepareStatement(sql);
		if(ps != null) {
			ps.close();
		}
	}
	
	
	@Test
	public void   prepareSqlIsBlank() throws Exception {
		String sql = "";
		ps = conn.prepareStatement(sql);
		if(ps != null) {
			ps.close();
		}
	}
	
	@Test
	public void   prepareSql() throws Exception {
		String sql = "";
		ps = conn.prepareStatement(sql);
		if(ps != null) {
			ps.close();
		}
		
	}
	
	@After
	public void  close() {
		
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
