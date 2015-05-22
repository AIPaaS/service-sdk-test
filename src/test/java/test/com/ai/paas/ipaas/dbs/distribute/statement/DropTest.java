package test.com.ai.paas.ipaas.dbs.distribute.statement;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;



public class DropTest extends SqlBase{

	@Test
	public void testDropTable(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement statement=null;
		try {
			conn=dataSource.getConnection();
			String sql="drop table  cust";
			statement=conn.createStatement();
			int result=statement.executeUpdate(sql);
			assertEquals(0,result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(null, statement, conn);
		}
		
		
	}
}
