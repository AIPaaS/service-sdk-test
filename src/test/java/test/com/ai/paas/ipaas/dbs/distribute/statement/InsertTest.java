package test.com.ai.paas.ipaas.dbs.distribute.statement;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

import com.ai.paas.ipaas.dbs.sequence.ISequenceClient;

public class InsertTest extends SqlBase {
	
	
	public void  testParamInsert() {
		
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		ISequenceClient sequenceClient = this.getSequenceClient();
        
        try{
        	conn = dataSource.getConnection();
        	String sql = " insert into cust(cust_id,name,cert_type,cert_code) values(?,?,?,?)";
        	Long custId = sequenceClient.nextValue("");
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, custId);
            ps.setString(2, "doubs");
            ps.setString(3, "身份证");
            ps.setString(4, "1234567890");
            int ret = ps.executeUpdate();
            assertEquals(1, ret);
            System.out.println("insert result:" + ret);
        }catch(SQLException e){
        	e.printStackTrace();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	this.close(null, preparedStatement, conn);
        }
	}
	
	
	
	public void  testCommonInsert() {
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		ISequenceClient sequenceClient = this.getSequenceClient();
        
        try{
        	conn = dataSource.getConnection();
        	Long custId = sequenceClient.nextValue("");
        	String sql = " insert into cust(cust_id,name,cert_type,cert_code) values("+ custId +",'doubs','身份证','1234567890')";
        	PreparedStatement ps = conn.prepareStatement(sql);
            int ret = ps.executeUpdate();
            assertEquals(1, ret);
        }catch(SQLException e){
        	e.printStackTrace();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	this.close(null, preparedStatement, conn);
        }
	}
	
	@Test
	public void testInsert(){
		DataSource dataSource=this.getDataSource();
		Connection conn=null;
		Statement state=null;
		try {
			conn=dataSource.getConnection();
			String sql=" insert into cust(cust_id,name,cert_type,cert_code) values('12','phoenix','身份证','1234567890')";
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
