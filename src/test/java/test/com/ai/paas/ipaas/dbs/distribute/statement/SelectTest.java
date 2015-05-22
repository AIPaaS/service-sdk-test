package test.com.ai.paas.ipaas.dbs.distribute.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

public class SelectTest extends SqlBase{

	public void  commonQueryTest() {
		
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			conn = dataSource.getConnection();
			//String sql = " select * from cust a where a.cust_id = 2";
			String sql = " select * from cust";
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
	
	public void testQuery(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement statement=null;
		String sql="select * from cust";
		ResultSet resultSet=null;
		try {
			conn=dataSource.getConnection();
			statement=conn.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
	   			int size = resultSet.getMetaData().getColumnCount();
	   			for(int i=1; i<=size; i++) {
	   				System.out.println(resultSet.getMetaData().getColumnName(i) + ":" + resultSet.getObject(i));
	   			}
	   		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(resultSet, statement, conn);
		}	
	}
	@Test
	public void testQueryWithCon(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement statement=null;
		String sql="select * from cust where cust_id>9";
		ResultSet resultSet=null;
		try {
			conn=dataSource.getConnection();
			statement=conn.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
	   			int size = resultSet.getMetaData().getColumnCount();
	   			for(int i=1; i<=size; i++) {
	   				System.out.println(resultSet.getMetaData().getColumnName(i) + ":" + resultSet.getObject(i));
	   			}
	   		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close(resultSet, statement, conn);
		}	
	}
	
	
	
	
	
	
	

}
