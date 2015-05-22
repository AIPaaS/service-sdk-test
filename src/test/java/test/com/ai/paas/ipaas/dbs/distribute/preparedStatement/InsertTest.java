package test.com.ai.paas.ipaas.dbs.distribute.preparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import static org.junit.Assert.*;
import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

import com.ai.paas.ipaas.dbs.sequence.ISequenceClient;

public class InsertTest extends SqlBase {
	
	@Test
	public void  testParamInsert() {
		
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		ISequenceClient sequenceClient = this.getSequenceClient();
        
        try{
        	conn = dataSource.getConnection();
        	String sql = " insert into cust(cust_id,name,cert_type,cert_code) values(?,?,?,?)";
        	//Long custId = sequenceClient.nextValue("");
        	Long custId = 10l;
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
	
	
	@Test
	public void  testCommonInsert() {
		DataSource dataSource = this.getDataSource();
		Connection conn  = null;
		PreparedStatement preparedStatement = null;
		ISequenceClient sequenceClient = this.getSequenceClient();
        
        try{
        	conn = dataSource.getConnection();
        	//Long custId = sequenceClient.nextValue("");
        	Long custId = 11l;
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
	
	
	
	
	
	
	

}
