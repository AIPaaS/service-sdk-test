package test.com.ai.paas.ipaas.dbs.distribute.preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

public class SelectTest extends SqlBase{
	
	public void  commonQueryTest() {
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			conn = dataSource.getConnection();
			String sql = " select * from cust a where a.cust_id = 2";
			preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
	   			int size = resultSet.getMetaData().getColumnCount();
	   			for(int i=1; i<=size; i++) {
	   				System.out.println(resultSet.getMetaData().getColumnName(i) + ":" + resultSet.getObject(i));
	   			}
	   		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(resultSet, preparedStatement, conn);
		}
		
	}
	
	
	public void  paramQueryTest() {
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			conn = dataSource.getConnection();
			String sql = " select * from cust a where a.cust_id = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, 2);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
	   			int size = resultSet.getMetaData().getColumnCount();
	   			for(int i=1; i<=size; i++) {
	   				System.out.println(resultSet.getMetaData().getColumnName(i) + ":" + resultSet.getObject(i));
	   			}
	   		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(resultSet, preparedStatement, conn);
		}
	}
	
	
	
	
	
	
	
	
	

}
