package test.com.ai.paas.ipaas.dbs.base;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
import com.ai.paas.ipaas.dbs.sequence.model.Sequence;
import com.ai.paas.ipaas.dbs.sequence.service.impl.SequenceServiceImpl;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class DataSourceTest  {
	
	
	private static String userName = "zh_ka@163.com";
	
	private static String  password = "123456";
	
	private static String  serviceId = "DBS001";
	
	private static String   authAddr = "http://10.1.228.198:14821/iPaas-Auth/service/check";

    int times = 0;

    public DataSourceTest(int times) {
        this.times = times;
    }

    public static void main(String[] args) throws Exception {
    	runTestCase();
    }

    public static void runTestCase() throws Exception {
        long start = System.currentTimeMillis();
        //testSelectCase();
        //testCreateCase();
        testDeleteCase();
        long end = System.currentTimeMillis();
        System.out.println("time cost:" + (end - start));
    }

    public static void testInsertCase() throws Exception {
    	
    	DistributedDataSource ds = new DistributedDataSource(userName,password,serviceId,authAddr);
        
        Connection conn = ds.getConnection();
        AuthDescriptor auth = new AuthDescriptor();
		auth.setAuthAdress(authAddr);
		auth.setPassword(password);
		auth.setServiceId(serviceId);
		auth.setUserName(userName);
    	ISequenceClient sequenceClient = SequenceFactory.getClient(auth);
        Long id  = sequenceClient.nextValue("cust_id_seq");
        String sql = " insert into cust(cust_id,name,cert_type,cert_code) values(?,?,?,?)";
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
//    	AuthDescriptor auth = new AuthDescriptor();
//		auth.setAuthAdress(authAddr);
//		auth.setPassword("DBS001");
//		auth.setServiceId("DBS001");
//		auth.setUserName("mvne@asiainfo.com");
//    	ISequenceClient sequenceClient = SequenceFactory.getClient(auth);
        DistributedDataSource ds =  new DistributedDataSource(userName,password,serviceId,authAddr);
        Connection conn = ds.getConnection();
        String sql = " DROP TABLE cust";
        PreparedStatement statement = null;

		PreparedStatement ps = conn.prepareStatement(sql);
		int result = ps.executeUpdate();
		printExecPlan();
		ps.close();
		conn.close();
        
        
    	
    }
    
    
public static void testSelectCase() throws Exception {
    	
        DistributedDataSource ds = new DistributedDataSource(userName,password,serviceId,authAddr);
        Connection conn = ds.getConnection();
        AuthDescriptor auth = new AuthDescriptor();
        Statement ps = null;
        String sql = " select * from cust a where a.cust_id = 1";
        //String sql = "";
        //String sql = "desc service_number";
   		ps = conn.createStatement();
   		ResultSet rs = ps.executeQuery(sql);
   		while(rs.next()) {
   			int size = rs.getMetaData().getColumnCount();
   			for(int i=1; i<=size; i++) {
   				System.out.println(rs.getMetaData().getColumnName(i) + ":" + rs.getObject(i));
   			}
   		}
        printExecPlan();
        rs.close();
        ps.close();
        conn.close();
        System.out.println("=============================================");
//        while(true) {
//        	  Thread.sleep(1000);
//        }
    }
    
    
    public static void testCreateCase() throws Exception {
    	DistributedDataSource ds = new DistributedDataSource(userName,password,serviceId,authAddr);
//		DistributeRuleAssist.setTableIndex(3);
		Connection conn = ds.getConnection();
		
		//String sql = " CREATE TABLE `cust` (`cust_id` int(11) NOT NULL,`name` varchar(45) DEFAULT NULL,`cert_type` varchar(45) DEFAULT NULL,`cert_code` varchar(45) DEFAULT NULL,PRIMARY KEY (`cust_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
		String sql = "CREATE TABLE river (river_id int(11) NOT NULL,name varchar(45)  NULL,cert_type varchar(45)  NULL,cert_code varchar(45)  NULL,PRIMARY KEY (river_id)) ENGINE=InnoDB  CHARACTER SET=utf8";
//		String sql = "ALTER TABLE `cust` ADD COLUMN `remark` VARCHAR(45) NULL AFTER `cert_code`";
//		String sql = "DROP TABLE `cust`";
//		String sql = "update cust set name='zjy'";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		printExecPlan();
		ps.close();
		conn.close();
	}
    
    
    public static void testDeleteCase() throws Exception {
		DistributedDataSource ds = new DistributedDataSource(userName,password,serviceId,authAddr);
//		DistributeRuleAssist.setTableIndex(3);
		Connection conn = ds.getConnection();
		String sql = " delete from cust t where t.cust_id = ?";
//		String sql = "ALTER TABLE `cust` ADD COLUMN `remark` VARCHAR(45) NULL AFTER `cert_code`";
//		String sql = "DROP TABLE `cust`";
//		String sql = "update cust set name='zjy'";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, 406l);
		ps.executeUpdate();
		printExecPlan();
		ps.close();
		conn.close();
	}
    
    
    
    
    public static void testAlterCase() throws Exception {
		DistributedDataSource ds = new DistributedDataSource(userName,password,serviceId,authAddr);
//		DistributeRuleAssist.setTableIndex(3);
		Connection conn = ds.getConnection();
		
		String sql = " ALTER TABLE cust change contact_num phone VARCHAR(45)";
		//String sql =  "ALTER TABLE cust  drop COLUMN remark";
		//String sql = "ALTER TABLE `cust` change COLUMN `remark` VARCHAR(45) NULL AFTER `cert_code`";
//		String sql = "DROP TABLE `cust`";
//		String sql = "update cust set name='zjy'";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		printExecPlan();
		ps.close();
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
