package com.ai.paas.ipaas.txs.dtm.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import com.ai.paas.ipaas.txs.common.protocol.AppDTLProtocol;
import com.ai.paas.ipaas.txs.common.protocol.AppPartitionTransactionProtocol;
import com.ai.paas.ipaas.txs.common.protocol.PartitionTransactionConfirmProtocol;
import com.ai.paas.ipaas.txs.common.protocol.type.DT_STATUS;
import com.ai.paas.ipaas.txs.dtm.TransactionContext;
import com.ai.paas.ipaas.txs.dtm.jdbc.util.JDBCPartitionTransactionUtil;
import com.ai.paas.ipaas.txs.dtm.transfer.DtLogSender;

public class DtSupportedConnection implements Connection {
	private Connection origin = null;

	private String url;

	private java.util.Properties info;

	private AppPartitionTransactionProtocol appPartitionTransactionProtocol;

	private Object lock = new Object();

	public DtSupportedConnection(Connection origin, String url,
			java.util.Properties info) throws SQLException {
		this.origin = origin;
		if (this.origin == null) {
			throw new SQLException("origin driver is null");
		}
		this.url = url;
		this.info = info;
		/**
		 * DtSupportedConnection下管理的数据连接，必须使用非自动提交
		 */
		this.origin.setAutoCommit(false);
		/**
		 * fixed bug: TransactionContext not init. 
		 * connection pool initial connection, but not in transaction manager.
		 */
		//this.beginTransaction();
	}

	private void beginTransaction() {
		if (appPartitionTransactionProtocol == null) {
			synchronized (lock) {
				if (appPartitionTransactionProtocol == null) {
					AppDTLProtocol appDTLProtocol = TransactionContext.get()
							.getLocalAppDTL();
					appPartitionTransactionProtocol = appDTLProtocol
							.getPartitionTransaction(
									JDBCPartitionTransactionUtil
											.getDBTransactionSignature(url,
													info, this), "SQL");
				}
			}
		}
	}

	private void endTransaction(String dtStatus) {
		/**
		 * 如果当前事务操作没有SQL可被提交，则不发送
		 */
		if (appPartitionTransactionProtocol != null) {
			synchronized (lock) {
				if (appPartitionTransactionProtocol != null) {
					try {
						appPartitionTransactionProtocol.finishClear();

						String globalDtStatus = appPartitionTransactionProtocol
								.getParent().getParent().getDtStatus();
						/**
						 * 1.DT_STATUS.RUNNING<br/>
						 * 如果整体事务还在运行中，则发送提交确认无意义<br/>
						 * 这种事务内的提前操作的子事务，不作为分布式事务的控制范围。
						 * 此操作一般发生在DataSource进行连通性测试<br/>
						 * 直接忽略<br/>
						 * 
						 * 2.DT_STATUS.ROLLBACKED 当前事务为回滚，则无需提供confirm操作
						 */
						if (DT_STATUS.RUNNING.equals(globalDtStatus)
								|| DT_STATUS.ROLLBACKED.equals(dtStatus)) {
							return;
						}
						/**
						 * 3.当前事务不是分布式事务，则无需发送confirm操作
						 */
						if (!appPartitionTransactionProtocol.getParent()
								.getParent().isDistributed()) {
							return;
						}

						appPartitionTransactionProtocol.setDtStatus(dtStatus);
						PartitionTransactionConfirmProtocol confirmProtocol = PartitionTransactionConfirmProtocol
								.getConfirmProtocol(
										appPartitionTransactionProtocol,
										dtStatus);
						DtLogSender
								.sendPartitionTransactionConfirmProtocol(confirmProtocol);
					} finally {
						appPartitionTransactionProtocol = null;
					}
				}
			}
		}
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.origin.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.origin.isWrapperFor(iface);
	}

	@Override
	public Statement createStatement() throws SQLException {
		this.beginTransaction();
		return new DtSupportedStatement(this.origin.createStatement(),
				this.url, this.info, null, this);
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		this.beginTransaction();
		return new DtSupportedPreparedStatement(
				this.origin.prepareStatement(sql), this.url, this.info, sql,
				this);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return this.origin.prepareCall(sql);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return this.origin.nativeSQL(sql);
	}

	/**
	 * 无法设置自动提交
	 */
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return this.origin.getAutoCommit();
	}

	@Override
	public void commit() throws SQLException {
		this.origin.commit();

		this.endTransaction(DT_STATUS.COMMITTED);
	}

	@Override
	public void rollback() throws SQLException {
		this.origin.rollback();

		this.endTransaction(DT_STATUS.ROLLBACKED);
	}

	@Override
	public void close() throws SQLException {
		this.origin.close();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return this.origin.isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return this.origin.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		this.origin.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return this.origin.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		this.origin.setCatalog(catalog);
	}

	@Override
	public String getCatalog() throws SQLException {
		return this.origin.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		this.origin.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return this.origin.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return this.origin.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		this.origin.clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		this.beginTransaction();
		return new DtSupportedStatement(this.origin.createStatement(
				resultSetType, resultSetConcurrency), this.url, this.info,
				null, this);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		this.beginTransaction();
		DtSupportedPreparedStatement stmt = new DtSupportedPreparedStatement(
				this.origin.prepareStatement(sql, resultSetType,
						resultSetConcurrency), this.url, this.info, sql, this);
		stmt.addConstructSqlProperty("resultSetConcurrency",
				resultSetConcurrency);
		return stmt;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return this.origin
				.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return this.origin.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		this.origin.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		this.origin.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException {
		return this.origin.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return this.origin.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return this.origin.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		this.origin.rollback(savepoint);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		this.origin.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		this.beginTransaction();
		return new DtSupportedStatement(this.origin.createStatement(
				resultSetType, resultSetConcurrency, resultSetHoldability),
				this.url, this.info, null, this);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		this.beginTransaction();
		DtSupportedPreparedStatement stmt = new DtSupportedPreparedStatement(
				this.origin.prepareStatement(sql, resultSetType,
						resultSetConcurrency, resultSetHoldability), this.url,
				this.info, sql, this);
		stmt.addConstructSqlProperty("resultSetConcurrency",
				resultSetConcurrency);
		stmt.addConstructSqlProperty("resultSetHoldability",
				resultSetHoldability);
		return stmt;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return this.origin
				.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		this.beginTransaction();
		DtSupportedPreparedStatement stmt = new DtSupportedPreparedStatement(
				this.origin.prepareStatement(sql, autoGeneratedKeys), this.url,
				this.info, sql, this);
		stmt.addConstructSqlProperty("autoGeneratedKeys", autoGeneratedKeys);
		return stmt;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		this.beginTransaction();
		DtSupportedPreparedStatement stmt = new DtSupportedPreparedStatement(
				this.origin.prepareStatement(sql, columnIndexes), this.url,
				this.info, sql, this);
		stmt.addConstructSqlProperty("columnIndexes", columnIndexes);
		return stmt;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		this.beginTransaction();
		DtSupportedPreparedStatement stmt = new DtSupportedPreparedStatement(
				this.origin.prepareStatement(sql, columnNames), this.url,
				this.info, sql, this);
		stmt.addConstructSqlProperty("columnNames", columnNames);
		return stmt;
	}

	@Override
	public Clob createClob() throws SQLException {
		return this.origin.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return this.origin.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return this.origin.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return this.origin.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return this.origin.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		this.origin.setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		this.origin.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return this.origin.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return this.origin.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		return this.origin.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		return this.origin.createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		this.origin.setSchema(schema);
	}

	@Override
	public String getSchema() throws SQLException {
		return this.origin.getSchema();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		this.origin.abort(executor);
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds)
			throws SQLException {
		this.origin.setNetworkTimeout(executor, milliseconds);
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return this.origin.getNetworkTimeout();
	}

}
