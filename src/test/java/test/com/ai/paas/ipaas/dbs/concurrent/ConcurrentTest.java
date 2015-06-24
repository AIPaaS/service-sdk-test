package test.com.ai.paas.ipaas.dbs.concurrent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ai.paas.ipaas.dbs.distribute.DistributedDataSource;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class ConcurrentTest {

	public static void main(String[] args) throws Exception {
		String userName = "zh_ka@163.com";

		String password = "123456";

		String serviceId = "DBS001";

		String authAddr = "http://10.1.228.198:14821/iPaas-Auth/service/check";

		final DistributedDataSource ds = new DistributedDataSource(userName,
				password, serviceId, authAddr);
		AuthDescriptor auth = new AuthDescriptor();
		auth.setAuthAdress(authAddr);
		auth.setPassword(password);
		auth.setServiceId(serviceId);
		auth.setUserName(userName);

		int thread_num = 5;
		runTestCase(ds, 99);

		List<Thread> threadPool = new ArrayList<Thread>();
		for (int index = 0; index < thread_num; index++) {

			final int NO = index + 100;

			Runnable run = new Runnable() {
				public void run() {
					try {
						// System.out.println("Thread:" + NO);
						runTestCase(ds, NO);
						// 业务逻辑
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			Thread testThread = new Thread(run);
			threadPool.add(testThread);
		}

		for (Thread testThread : threadPool) {
			testThread.start();
		}

		Thread.sleep(1000000L);
		// runTestCase(ds,101);
	}

	public static void runTestCase(DistributedDataSource ds, int n)
			throws Exception {

		// testDescCase(ds);
		testSelectCaseUser(ds, n);

	}

	public static void testDescCase(DistributedDataSource ds) throws Exception {

		Connection conn = ds.getConnection();
		// String sql = " desc cust2";

		String sql = "desc cust2";

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				 int size = rs.getMetaData().getColumnCount();
				 for(int i=1; i<=size; i++) {
				 System.out.println(rs.getMetaData().getColumnName(i) + ":"
				 + rs.getObject(i));
				 }
			}
			ps.execute();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			ps.close();
			conn.close();
		}
	}

	public static void testDeleteCase(DistributedDataSource ds)
			throws Exception {

		Connection conn = ds.getConnection();
		String sql = " delete from project t where t.project_id = ?";
		// String sql =
		// "ALTER TABLE `cust` ADD COLUMN `remark` VARCHAR(45) NULL AFTER `cert_code`";
		// String sql = "DROP TABLE `cust`";
		// String sql = "update cust set name='zjy'";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, 1);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public static void testSelectCaseUser(DistributedDataSource ds, int n)
			throws Exception {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			conn = ds.getConnection();
			String sql = " select * from project a where a.project_id = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, n);
			long start = System.currentTimeMillis();
			resultSet = preparedStatement.executeQuery();
			long end = System.currentTimeMillis();
			System.out.println("time cost:(" + n + ")" + (end - start));
			/*
			 * while(resultSet.next()) { int size =
			 * resultSet.getMetaData().getColumnCount(); for(int i=1; i<=size;
			 * i++) {
			 * System.out.println(resultSet.getMetaData().getColumnName(i) + ":"
			 * + resultSet.getObject(i)); } }
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			preparedStatement.close();
			resultSet.close();
		}
	}

	public static void testInsertCase(DistributedDataSource ds, int n)
			throws Exception {
		Connection conn = ds.getConnection();
		String sql = " insert into project(project_id,name) values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, n);
		ps.setString(2, "doubs");
		int ret = ps.executeUpdate();
		System.out.println("insert result:" + ret);
		conn.close();
	}

	public static void testUpdateCase(DistributedDataSource ds)
			throws Exception {

		// DistributeRuleAssist.setTableIndex(3);
		Connection conn = ds.getConnection();
		String sql = "update project set name=? where cust_id=?";
		// String sql =
		// "ALTER TABLE `cust` ADD COLUMN `remark` VARCHAR(45) NULL AFTER `cert_code`";
		// String sql = "DROP TABLE `cust`";
		// String sql = "update cust set name='zjy'";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "river");
		ps.setLong(2, 1);
		ps.executeUpdate();

		ps.close();
		conn.close();
	}
}
