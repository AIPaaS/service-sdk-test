package test.com.ai.paas.ipaas.dbs.concurrent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

//import org.apache.commons.dbcp.BasicDataSource;

import com.ai.paas.ipaas.dbs.distribute.DistributedDataSource;
import com.ai.paas.ipaas.txs.dtm.TransactionContext;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class ConcurrentTest
{
  public static void main(String[] args)
    throws Exception
  {
    String userName = "xingyx@asiainfo.com";

    String password = "123456";

    String serviceId = "DBS017";

    String authAddr = "http://10.1.228.198:14821/iPaas-Auth/service/check";

    final DistributedDataSource ds = new DistributedDataSource(userName, password, serviceId, authAddr);

    AuthDescriptor auth = new AuthDescriptor();
    auth.setAuthAdress(authAddr);
    auth.setPassword(password);
    auth.setServiceId(serviceId);
    auth.setUserName(userName);

    final Integer thread_num = 100;
    final Integer repeat_num = 10000;
    final String operator = "select";
//    Integer thread_num = Integer.valueOf(Integer.parseInt(args[0]));
//    final Integer repeat_num = Integer.valueOf(Integer.parseInt(args[1]));
//    final String operator = args[2];
    System.out.println(thread_num);
    System.out.println(repeat_num);

    final String database = "";
//    final String database = args[3];

    final DataSource dataSource = null;
//    final DataSource dataSource = setupDataSource();
    final AtomicLong total = new AtomicLong();
//    runTestCase(ds, 1000000, operator, database, dataSource, total);
    List<Thread> threadPool = new ArrayList<>();

    for (int index = 0; index < thread_num.intValue(); index++) {
      final int test = index;
      Runnable run = new Runnable()
      {
        public void run() {
          try {
            for (int i = 0; i < repeat_num; i++)
            {
              int num = test * repeat_num + i;
              ConcurrentTest.runTestCase(ds, num, operator, database, dataSource, total);
            }
          }
          catch (Exception e) {
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

//    while (true)
//    {
//      Thread.sleep(10000L);
//      int num = thread_num.intValue() * (repeat_num.intValue() - 10);
//
//      System.out.println("num: " + num);
//      long totalTime = total.longValue();
//      System.out.println("totalTime :" + totalTime);
//      long average = totalTime / num;
//      System.out.println("the average time is " + average + " ms");
//    }
  }

  public static void runTestCase(DistributedDataSource ds, int n, String operator, String database, DataSource dataSource, AtomicLong total)
    throws Exception
  {
    switch (operator)
    {
    case "insert":
      testInsertCase(ds, n, total);
      break;
    case "select":
      testSelectCaseUser(ds, n, total);
      break;
    case "update":
      testUpdateCase(ds, n, total);
      break;
    case "delete":
      testDeleteCase(ds, n, total);
      break;
    case "basicInsert":
      basicDbcpInsert(dataSource, total, n);
      break;
    case "basicSelect":
      basicSelect(dataSource, total, n);
      break;
    case "basicUpdate":
      basicUpdate(dataSource, total, n);
      break;
    case "basicDelete":
      basicDelete(dataSource, total, n);
    }
  }

  public static void basicUpdate(DataSource dataSource, AtomicLong total, int n)
  {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    try {
      long start = System.currentTimeMillis();
      conn = dataSource.getConnection();
      conn.setAutoCommit(true);
      String sql = "update project_0 set name=? where project_id=?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, "river");
      ps.setLong(2, n);
      ps.executeUpdate();
      long end = System.currentTimeMillis() - start;
      if (n != 0)
      {
        total.addAndGet(end);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace(); } finally {
      try {
        if (resultSet != null) resultSet.close();  } catch (Exception e) {
      }try { if (ps != null) ps.close();  } catch (Exception e) {
      }try { if (conn != null) conn.close();  } catch (Exception e) {  }
    }
  }

  public static void basicDelete(DataSource dataSource, AtomicLong total, int n) { Connection conn = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    try {
      long start = System.currentTimeMillis();
      conn = dataSource.getConnection();
      conn.setAutoCommit(true);
      String sql = " delete from project_0  where project_id = ?";

      ps = conn.prepareStatement(sql);
      ps.setLong(1, n);
      ps.executeUpdate();
      long end = System.currentTimeMillis() - start;
      if (n != 0)
      {
        total.addAndGet(end);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace(); } finally {
      try {
        if (resultSet != null) resultSet.close();  } catch (Exception e) {
      }try { if (ps != null) ps.close();  } catch (Exception e) {
      }try { if (conn != null) conn.close();  } catch (Exception e) {
      }
    } } 
  public static void basicSelect(DataSource dataSource, AtomicLong total, int n) {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      long start = System.currentTimeMillis();
      conn = dataSource.getConnection();
      conn.setAutoCommit(true);

      String sql = " select * from project_0 where project_id = ?";
      preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setInt(1, n);

      resultSet = preparedStatement.executeQuery();
      long end = System.currentTimeMillis() - start;
      if (n != 0)
      {
        total.addAndGet(end);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace(); } finally {
      try {
        if (resultSet != null) resultSet.close();  } catch (Exception e) {
      }try { if (preparedStatement != null) preparedStatement.close();  } catch (Exception e) {
      }try { if (conn != null) conn.close();  } catch (Exception e)
      {
      }
    }
  }

  public static void basicDbcpInsert(DataSource dataSource, AtomicLong total, int n) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rset = null;
    try {
      long start = System.currentTimeMillis();
      conn = dataSource.getConnection();
      conn.setAutoCommit(true);

      String sql = " insert into project_0(project_id,name) values(?,?)";
      stmt = conn.prepareStatement(sql);
      stmt.setLong(1, n);
      stmt.setString(2, "river");
      stmt.executeUpdate();
      long end = System.currentTimeMillis() - start;
      if (n > 9)
      {
        total.addAndGet(end);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace(); } finally {
      try {
        if (rset != null) rset.close();  } catch (Exception e) {
      }try { if (stmt != null) stmt.close();  } catch (Exception e) {
      }try { if (conn != null) conn.close();  } catch (Exception e) {  }
    }
  }

//  public static DataSource setupDataSource() { BasicDataSource dataSource = new BasicDataSource();
//    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//    dataSource.setUrl("jdbc:mysql://10.125.3.250:39311/pocrdb11");
//    dataSource.setUsername("pocrdbusr11");
//    dataSource.setPassword("poc@11!");
//    dataSource.setInitialSize(200);
//    dataSource.setMaxActive(500);
//    dataSource.setMaxIdle(4);
//    dataSource.setMaxWait(10000L);
//    dataSource.setTestWhileIdle(true);
//    return dataSource;
//  }

  public static void basicInsert(String database)
  {
    String url = "jdbc:mysql://localhost/EMP";
    String user = "username";
    String password = "password";
    Connection conn = null;
    PreparedStatement stmt = null;
    if (database.equals("devrdb12"))
    {
      url = "jdbc:mysql://10.1.228.202:31306/devrdb12";
      user = "devrdbusr12";
      password = "devrdbusr12";
      try {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(true);
        long end = System.currentTimeMillis();
        System.out.println("connect devrdb12 cost time :" + (end - start));
        start = System.currentTimeMillis();
        String sql = " insert into project_0(project_id,name) values(?,?)";
        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, 100000L);
        stmt.setString(2, "river");
        stmt.executeUpdate();
        end = System.currentTimeMillis();
        System.out.println("statement cost time :" + (end - start));
      }
      catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          conn.close();
          stmt.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    else {
      url = "jdbc:mysql://10.1.228.202:31306/devrdb13";
      user = "devrdbusr13";
      password = "devrdbusr13";
      try {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(true);
        long end = System.currentTimeMillis();
        System.out.println("connect devrdb13 cost time :" + (end - start));
        start = System.currentTimeMillis();
        String sql = " insert into project_1(project_id,name) values(?,?)";
        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, 100000L);
        stmt.setString(2, "river");
        stmt.executeUpdate();
        end = System.currentTimeMillis();
        System.out.println("statement cost time :" + (end - start));
      }
      catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void testDescCase(DistributedDataSource ds) throws Exception
  {
    Connection conn = ds.getConnection();

    String sql = "desc cust2";

    PreparedStatement ps = null;
    try {
      ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int size = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= size; i++) {
          System.out.println(rs.getMetaData().getColumnName(i) + ":" + rs.getObject(i));
        }
      }

      ps.execute();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      ps.close();
      conn.close();
    }
  }

  public static void testDeleteCase(DistributedDataSource ds, int n, AtomicLong total) throws Exception
  {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
      long start = System.currentTimeMillis();
      conn = ds.getConnection();

      String sql = " delete from project  where project_id = ?";

      ps = conn.prepareStatement(sql);
      ps.setLong(1, n);
      ps.executeUpdate();
      long end = System.currentTimeMillis() - start;
      if (n != 0)
      {
        total.addAndGet(end);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    } finally {
      ps.close();
      conn.close();
    }
  }

  public static void testSelectCaseUser(DistributedDataSource ds, int n, AtomicLong total)
    throws Exception
  {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      long start = System.currentTimeMillis();
      conn = ds.getConnection();

      String sql = "select * from cust_liwx_attr cust_id = ?";
      preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setInt(1, n);

      resultSet = preparedStatement.executeQuery();
      long end = System.currentTimeMillis() - start;
      if (n != 0)
      {
        total.addAndGet(end);
      }

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      conn.close();
      preparedStatement.close();
      resultSet.close();
    }
  }

  public static void testInsertCase(DistributedDataSource ds, int n, AtomicLong total) throws Exception
  {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
      long start = System.currentTimeMillis();
      TransactionContext.initVisualContext();
      conn = ds.getConnection();

      String sql = " insert into cust_liwx_attr(cust_id,attr_name) values(?,?)";
//      String sql = " insert into project(project_id,name) values(?,?)";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, n);
      ps.setString(2, "attr"+n);
      ps.executeUpdate();
      
      long end = System.currentTimeMillis() - start;
      if (n > 9)
      {
        total.addAndGet(end);
      }
      
	TransactionContext.clear();
	conn.commit();

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      conn.close();
      ps.close();
    }
  }

  public static void testUpdateCase(DistributedDataSource ds, int n, AtomicLong total)
    throws Exception
  {
    Connection conn = null;
    PreparedStatement ps = null;
    try
    {
      long start = System.currentTimeMillis();
      conn = ds.getConnection();

      String sql = "update project set name=? where project_id=?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, "river");
      ps.setLong(2, n);
      ps.executeUpdate();
      long end = System.currentTimeMillis() - start;
      if (n != 0)
      {
        total.addAndGet(end);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    } finally {
      ps.close();
      conn.close();
    }
  }
}