package test.com.ai.paas.ipaas.dbs.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ai.paas.ipaas.dbs.distribute.DistributedDataSource;
import com.ai.paas.ipaas.dbs.distribute.SqlExecPlan;
import com.ai.paas.ipaas.dbs.sequence.ISequenceClient;
import com.ai.paas.ipaas.dbs.sequence.SequenceFactory;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class DataSourceSingleTest {

	public DataSourceSingleTest() {
	}
	
    public static void main(String[] args) throws Exception {
        runTestCase();
    }


    public static void runTestCase() throws Exception {
        long start = System.currentTimeMillis();
        testUpdateCase();
        long end = System.currentTimeMillis();
        System.out.println("time cost:" + (end - start));
    }
    
	 
    public static void testCreateTableCase() throws Exception {
		DistributedDataSource ds = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
//		DistributeRuleAssist.setTableIndex(3);
		Connection conn = ds.getConnection();
		
		String sql = " CREATE TABLE `service_number` (`service_id` int(11) NOT NULL,`name` varchar(45) DEFAULT NULL,`cert_type` varchar(45) DEFAULT NULL,`cert_code` varchar(45) DEFAULT NULL,PRIMARY KEY (`service_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
//		String sql = "ALTER TABLE `cust` ADD COLUMN `remark` VARCHAR(45) NULL AFTER `cert_code`";
//		String sql = "DROP TABLE `cust`";
//		String sql = "update cust set name='zjy'";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		printExecPlan();
		ps.close();
		conn.close();
	}
    
 public static void testInsertCase() throws Exception {
    	
        DistributedDataSource ds = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
        Connection conn = ds.getConnection();
        AuthDescriptor auth = new AuthDescriptor();
		auth.setAuthAdress("http://10.1.228.198:14821/iPaas-Auth/service/check");
		auth.setPassword("18501114411");
		auth.setServiceId("DBS001");
		auth.setUserName("18501114411");
    	ISequenceClient sequenceClient = SequenceFactory.getClient(auth);
        Long id  = sequenceClient.nextValue("cust_id_seq");
        String sql = " insert into service_number(service_id,name,cert_type,cert_code) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.setString(2, "doubs");
        ps.setString(3, "身份证");
        ps.setString(4, "1234567890");
        int ret = ps.executeUpdate();
        System.out.println("insert result:" + ret);
//        sql = " update cust set name=? where cust_id = ?";
//          ps = conn.prepareStatement(sql);
//        ps.setString(1, "zhangxf");
//        ps.setLong(2, id);
//         ret = ps.executeUpdate();
//        System.out.println("delete result:" + ret);
        
        printExecPlan();
        
        conn.close();
    }
    
    public static void testDropCase() throws Exception {
    	AuthDescriptor auth = new AuthDescriptor();
 		auth.setAuthAdress("http://10.1.228.198:14821/iPaas-Auth/service/check");
 		auth.setPassword("1234567");
 		auth.setServiceId("DBS009");
 		auth.setUserName("mapl_590@163.com");
    	ISequenceClient sequenceClient = SequenceFactory.getClient(auth);
        DistributedDataSource ds = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
        Connection conn = ds.getConnection();
        String sql = " DROP TABLE service_number";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		printExecPlan();
		ps.close();
		conn.close();
    }
    
   public static void testUpdateCase() throws Exception {
	    DistributedDataSource ds = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
        Connection conn = ds.getConnection();
        String sql = " update service_number set name = 'zhangxf2' where  service_id = 112";
        Statement st = conn.createStatement();
        st.execute(sql);
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.executeUpdate();
		printExecPlan();
		st.close();
		conn.close();
	   
   }
    
    
    
 public static void testSelectCase() throws Exception {
    	
        DistributedDataSource ds = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
        Connection conn = ds.getConnection();
        AuthDescriptor auth = new AuthDescriptor();
        PreparedStatement ps = null;
        String sql = " select * from service_number a where a.service_id = ?";
		ps = conn.prepareStatement(sql);
		ps.setLong(1, 112l);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int size = rs.getMetaData().getColumnCount();
			for(int i=1; i<=size; i++) {
				System.out.println(rs.getMetaData().getColumnName(i) + ":" + rs.getObject(i));
			}
		}
        printExecPlan();
        
        conn.close();
    }
 
 
 public static void testStatementSelectCase() throws Exception {
 	
     DistributedDataSource ds = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
     Connection conn = ds.getConnection();
     Statement ps = null;
     String sql = " select * from service_number a where a.service_id = 112";
		ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		while(rs.next()) {
			int size = rs.getMetaData().getColumnCount();
			for(int i=1; i<=size; i++) {
				System.out.println(rs.getMetaData().getColumnName(i) + ":" + rs.getObject(i));
			}
		}
     printExecPlan();
     
     conn.close();
 }
    
    
 private static void printExecPlan() {
		Map<String,List<String>>  sqlMap =SqlExecPlan.getSqlexecPlan();
		Map<String,List<String>>  tableNameMap =SqlExecPlan.getTableNamePlan();
		if(sqlMap != null) {
			 Set<String> keySet = sqlMap.keySet();
			 if(!CollectionUtil.isEmpty(keySet)) {
				 for(String key : keySet ){
					 List<String>  list = sqlMap.get(key);
					 if(!CollectionUtil.isEmpty(list)) {
						 for(String sqlExec :  list){
							 System.out.println(sqlExec + "=============================");
						 }
					 }
				 }
			 }
		}
		
	    if(tableNameMap != null) {
	    	Set<String> keySet = tableNameMap.keySet();
	    	 if(!CollectionUtil.isEmpty(keySet)) {
				 for(String key : keySet ){
					 List<String>  list = tableNameMap.get(key);
					 if(!CollectionUtil.isEmpty(list)) {
						 for(String sqlExec :  list){
							 System.out.println(sqlExec + "=============================");
						 }
					 }
				 }
			 }
	    	
	    }
	}
    
	  
	

}
