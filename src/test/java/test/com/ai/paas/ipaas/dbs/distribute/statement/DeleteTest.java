package test.com.ai.paas.ipaas.dbs.distribute.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

public class DeleteTest extends SqlBase{
	
	@Test
	public void testDelete(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement state=null;
		String sql="delete from cust where cust_id=12";
		try {
			conn=dataSource.getConnection();
			state=conn.createStatement();
			state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(null, state, conn);
		}
		
	}
}
