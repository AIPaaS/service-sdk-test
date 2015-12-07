package com.ai.paas.ipaas.txs.dtm.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.ai.paas.ipaas.txs.dtm.controller.IPartitionTransactionController;

/**
 * JDBC物理连接控制器<br/>
 * 用于封装官方的JDBC，如：mysql connection<br/>
 * 
 * 
 * @Title: JDBCConnectionController.java 
 * @author wusheng
 * @date 2015年3月19日 上午10:25:41 
 *
 */
public class JDBCConnectionController implements
		IPartitionTransactionController {
	private Connection conn;

	/**
	 * 受管制的连接（可为虚拟连接）<br/>
	 * <br/>
	 * 注意：默认设置为非自动提交
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public JDBCConnectionController(Connection conn) throws SQLException{
		this.conn = conn;
		conn.setAutoCommit(false);
	}
	
	@Override
	public void commit() throws SQLException {
		conn.commit();
	}

	@Override
	public void rollback() throws SQLException {
		conn.rollback();
	}

	@Override
	public void clear() throws SQLException {
		conn.close();
	}

	@Override
	public boolean isHighPriority() {
		return true;
	}



}
