package test.com.ai.paas.ipaas.dbs.distribute.preparedStatement;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.ai.paas.ipaas.dbs.sequence.ISequenceClient;

import test.com.ai.paas.ipaas.dbs.distribute.base.SqlBase;

public class UpdateTest extends SqlBase{

	@Test
	public void testUpdate(){
		
		DataSource dataSource = this.getDataSource();
		ISequenceClient sequenceClient  = this.getSequenceClient();
		Connection conn  = null;
		PreparedStatement ps = null;
		try{
			conn = dataSource.getConnection();
			String sql = " insert into cust(cust_id,name,cert_type,cert_code) values(?,?,?,?)";
        	Long custId = sequenceClient.nextValue("cust_id_seq");
        	ps = conn.prepareStatement(sql);
            ps.setLong(1, custId);
            ps.setString(2, "doubs");
            ps.setString(3, "身份证");
            ps.setString(4, "1234567890");
            int ret = ps.executeUpdate();
            assertEquals(1, ret);
            
            sql = "update  cust set  name = ? where  user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "驾驶证");
            ps.setLong(2, custId);
            ret = ps.executeUpdate();
            assertEquals(1, ret);
            
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(null, ps, conn);
		}
	}
	
}
