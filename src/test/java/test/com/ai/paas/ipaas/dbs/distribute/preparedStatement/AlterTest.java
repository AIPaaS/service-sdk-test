package test.com.ai.paas.ipaas.dbs.distribute.preparedStatement;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

public class AlterTest extends SqlBase{
	

	@Test
	public void  testAlterTable(){
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		try{
			conn = dataSource.getConnection();
			String sql = "ALTER TABLE cust change contact_num phone VARCHAR(45)";
			preparedStatement = conn.prepareStatement(sql);
			boolean result = preparedStatement.execute();
			assertEquals(true,result);
			sql = "ALTER TABLE cust change phone contact_num VARCHAR(45)";
			preparedStatement = conn.prepareStatement(sql);
			result = preparedStatement.execute();
			assertEquals(true,result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(null, preparedStatement, conn);
		}
	}
	
}
