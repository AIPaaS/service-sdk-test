package test.com.ai.paas.ipaas.dbs.distribute.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

public class UpdateTest extends SqlBase{

	
	public void updateTest(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement state=null;
		String sql="update  cust set  name ='river' where  cust_id = 10";
		try {
			conn=dataSource.getConnection();
			state=conn.createStatement();
			state.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(null, state, conn);
		}	
	}
	@Test
	public void updateConTest(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement state=null;
		String sql="update  cust set  name ='river',cert_type='11' where  cust_id = 10";
		try{
			conn=dataSource.getConnection();
			state=conn.createStatement();
			state.execute(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally{
			this.close(null, state, conn);
		}
	}
}
