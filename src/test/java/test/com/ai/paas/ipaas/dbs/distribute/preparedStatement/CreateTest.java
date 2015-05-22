package test.com.ai.paas.ipaas.dbs.distribute.preparedStatement;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

public class CreateTest extends SqlBase{
	
	@Test
	public void  testCreateTable(){
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		try{
			conn = dataSource.getConnection();
			String sql = "CREATE TABLE cust (cust_id int(11) NOT NULL,name varchar(45)  NULL,cert_type varchar(45)  NULL,cert_code varchar(45)  NULL,PRIMARY KEY (cust_id)) ENGINE=InnoDB  CHARACTER SET=utf8";
			preparedStatement = conn.prepareStatement(sql);
			boolean result = preparedStatement.execute();
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
