package com.ai.paas.ipaas.txs.dtm.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import com.ai.paas.ipaas.txs.common.protocol.AppDTLProtocol;
import com.ai.paas.ipaas.txs.common.protocol.AppPartitionTransactionProtocol;
import com.ai.paas.ipaas.txs.common.protocol.jdbc.SQLEntity;
import com.ai.paas.ipaas.txs.dtm.TransactionContext;
import com.ai.paas.ipaas.txs.dtm.jdbc.util.JDBCPartitionTransactionUtil;
import com.ai.paas.ipaas.util.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DtSupportedStatement implements Statement {
	private static final String STMT_TYPE = "DtSupportedStatement";

	private Statement origin;

	private JsonArray sqlLogs;

	/**
	 * 通过prepareStatement方法，传入的SQL结构
	 */
	private SQLEntity constructSql = null;

	public DtSupportedStatement(Statement origin, String url,
			java.util.Properties info, String sql, DtSupportedConnection conn) {
		this.origin = origin;
		AppDTLProtocol appDTLProtocol = TransactionContext.get()
				.getLocalAppDTL();
		AppPartitionTransactionProtocol appPartitionTransactionProtocol = appDTLProtocol
				.getPartitionTransaction(JDBCPartitionTransactionUtil
						.getDBTransactionSignature(url, info, conn), "SQL");

		synchronized (appPartitionTransactionProtocol) {
			JsonObject sqlLogEntity = appPartitionTransactionProtocol.getInfo();
			if (sqlLogEntity.has("SQLS")) {
				sqlLogs = sqlLogEntity.getAsJsonArray("SQLS");
			} else {
				sqlLogs = new JsonArray();
				sqlLogEntity.add("SQLS", sqlLogs);
			}
			if (!StringUtil.isBlank(sql)) {
				constructSql = new SQLEntity();
				constructSql.setSql(STMT_TYPE, "DtSupportedPreparedStatement",
						sql);
			}
		}
	}

	/**
	 * 为存在的construactSqlProperty添加值
	 * 
	 * @param property
	 * @param value
	 */
	void addConstructSqlProperty(String property, Object value) {
		if (constructSql != null) {
			constructSql.addProperty(property, value);
		}
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "executeQuery", sql);
		return this.origin.executeQuery(sql);
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "executeUpdate", sql);
		return this.origin.executeUpdate(sql);
	}

	@Override
	public void close() throws SQLException {
		this.origin.close();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return this.origin.getMaxFieldSize();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		this.origin.setMaxFieldSize(max);
	}

	@Override
	public int getMaxRows() throws SQLException {
		return this.origin.getMaxRows();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		this.origin.setMaxRows(max);
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		this.origin.setEscapeProcessing(enable);
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return this.origin.getQueryTimeout();
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		this.origin.setQueryTimeout(seconds);
	}

	@Override
	public void cancel() throws SQLException {
		this.origin.cancel();
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
	public void setCursorName(String name) throws SQLException {
		this.origin.setCursorName(name);
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "execute", sql);
		return this.origin.execute(sql);
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		return this.origin.getResultSet();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return this.origin.getUpdateCount();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return this.origin.getMoreResults();
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		this.origin.setFetchDirection(direction);
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return this.origin.getFetchDirection();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		this.origin.setFetchSize(rows);
	}

	@Override
	public int getFetchSize() throws SQLException {
		return this.origin.getFetchSize();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return this.origin.getResultSetConcurrency();
	}

	@Override
	public int getResultSetType() throws SQLException {
		return this.origin.getResultSetType();
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "addBatch", sql);
		this.origin.addBatch(sql);
	}

	@Override
	public void clearBatch() throws SQLException {
		this.origin.clearBatch();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		return this.origin.executeBatch();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return this.origin.getConnection();
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		return this.origin.getMoreResults(current);
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return this.origin.getGeneratedKeys();
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "executeUpdate", sql);
		entity.addProperty("autoGeneratedKeys", autoGeneratedKeys);
		return this.origin.executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "executeUpdate", sql);
		entity.addProperty("columnIndexes", columnIndexes);
		return this.origin.executeUpdate(sql, columnIndexes);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "executeUpdate", sql);
		entity.addProperty("columnNames", columnNames);
		return this.origin.executeUpdate(sql, columnNames);
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "execute", sql);
		entity.addProperty("autoGeneratedKeys", autoGeneratedKeys);
		return this.origin.execute(sql, autoGeneratedKeys);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "execute", sql);
		entity.addProperty("columnIndexes", columnIndexes);
		return this.origin.execute(sql, columnIndexes);
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "execute", sql);
		entity.addProperty("columnNames", columnNames);
		return this.origin.execute(sql, columnNames);
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return this.origin.getResultSetHoldability();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return this.origin.isClosed();
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		this.origin.setPoolable(poolable);
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return this.origin.isPoolable();
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		this.origin.closeOnCompletion();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return this.origin.isCloseOnCompletion();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.origin.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.origin.isWrapperFor(iface);
	}
}
