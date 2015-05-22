package test.com.ai.paas.ipaas.dbs.distribute.statement;


import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;


public class CreateTest extends SqlBase{
	
	@Test
	public void testCreatTable(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement statement=null;
		try {
			conn=dataSource.getConnection();
			String sql="CREATE TABLE cust (cust_id int(11) NOT NULL,name varchar(45)  NULL,cert_type varchar(45)  NULL,cert_code varchar(45)  NULL,PRIMARY KEY (cust_id)) ENGINE=InnoDB  CHARACTER SET=utf8";
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
