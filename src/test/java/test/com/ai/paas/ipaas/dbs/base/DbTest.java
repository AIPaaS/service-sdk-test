package test.com.ai.paas.ipaas.dbs.base;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ai.paas.ipaas.dbs.distribute.DistributedDataSource;
import com.ai.paas.ipaas.dbs.distribute.SqlExecPlan;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;

public class DbTest {
	
	
	public static void main(String[] args) throws Exception {
		
		testCase8();

	}
	
	public static void testCase8() throws Exception {
		DistributedDataSource ds = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
		Connection conn = ds.getConnection();
		String sql = " DROP TABLE cust";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		printExecPlan();
		ps.close();
		conn.close();
	}
	
	
	public static  void  testCase1() throws Exception {
		
		DistributedDataSource dataSource = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
		
		InputStream stream = DbTest.class.getResourceAsStream("/table_create.sql");
		
	    BufferedReader  reader = new BufferedReader(new InputStreamReader(stream));
	    String readLine = null;
			readLine = reader.readLine();

		StringBuffer buffer = new StringBuffer();
	    while(readLine != null){
	    	buffer.append(readLine);
	    	readLine = reader.readLine();
	    }
	    
	    String sql =  buffer.toString();

	    
	    Connection conn = dataSource.getConnection();
	    
	    PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();
		
	}
	
public static  void  testCase2() throws Exception {
		
		DistributedDataSource dataSource = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
	    
	    String sql =  "drop table tf_chl_channel";

	    Connection conn = dataSource.getConnection();
	    
	    PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();
		
	}


   
	
	
	public static void testCase7() throws Exception {
		DistributedDataSource ds = new DistributedDataSource("mapl_590@163.com","1234567","DBS009","http://10.1.228.198:14821/iPaas-Auth/service/check");
		
//		DistributeRuleAssist.setTableIndex(3);
		Connection conn = ds.getConnection();
		
		String sql = " CREATE TABLE `cust` (`cust_id` int(11) NOT NULL,`name` varchar(45) DEFAULT NULL,`cert_type` varchar(45) DEFAULT NULL,`cert_code` varchar(45) DEFAULT NULL,PRIMARY KEY (`cust_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
//		String sql = "ALTER TABLE `cust` ADD COLUMN `remark` VARCHAR(45) NULL AFTER `cert_code`";
//		String sql = "DROP TABLE `cust`";
//		String sql = "update cust set name='zjy'";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		
//		while(rs != null && rs.next()) {
//			int size = rs.getMetaData().getColumnCount();
//			for(int i=1; i<=size; i++) {
//				System.out.println(rs.getMetaData().getColumnName(i) + ":" + rs.getObject(i));
//			}
//
//		}
		/*
		List<Thread> tList = new ArrayList<Thread>();
		for(int i=0; i<10; i++) {
			Thread t = new TestThread(conn, i);
			tList.add(t);
			t.start();
		}
		
		for(Thread t : tList) {
			t.join();
		}
		*/
		
		printExecPlan();
		
		ps.close();
		
		conn.close();
	}

	private static void printExecPlan() {
		Map<String,List<String>>  sqlMap =SqlExecPlan.getSqlexecPlan();
		Map<String,List<String>>  tableNameMap =SqlExecPlan.getTableNamePlan();
		if(sqlMap != null) {
			 Set<String> keySet = sqlMap.keySet();
			 if(CollectionUtil.isEmpty(keySet)) {
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
	}
	
	
	

} 
