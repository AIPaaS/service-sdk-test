package test.com.ai.paas.ipaas.dbs.distribute.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

public class AlterTest extends SqlBase{

	@Test 
	public void testAlterTable(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement statement=null;
		try {
			conn=dataSource.getConnection();
			String sql="ALTER TABLE cust modify column name varchar(60)";
			statement=conn.createStatement();
			int result=statement.executeUpdate(sql);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(null, statement, conn);
		}
		
	}
}
