package com.ai.paas.ipaas.txs.dtm.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import com.ai.paas.ipaas.txs.common.protocol.AppDTLProtocol;
import com.ai.paas.ipaas.txs.common.protocol.AppPartitionTransactionProtocol;
import com.ai.paas.ipaas.txs.common.protocol.jdbc.SQLEntity;
import com.ai.paas.ipaas.txs.common.util.TimeUtil;
import com.ai.paas.ipaas.txs.dtm.TransactionContext;
import com.ai.paas.ipaas.txs.dtm.jdbc.util.JDBCPartitionTransactionUtil;
import com.ai.paas.ipaas.util.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DtSupportedPreparedStatement implements PreparedStatement {
	private static final String STMT_TYPE = "DtSupportedPreparedStatement";
	
	private PreparedStatement origin;

	private JsonArray sqlLogs;
	
	/**
	 * 通过prepareStatement方法，传入的SQL结构
	 */
	private SQLEntity constructSql = null;
	
	public DtSupportedPreparedStatement(PreparedStatement origin, String url,
			java.util.Properties info, String sql, DtSupportedConnection conn) {
		this.origin = origin;
		AppDTLProtocol appDTLProtocol = TransactionContext.get()
				.getLocalAppDTL();
		AppPartitionTransactionProtocol appPartitionTransactionProtocol = appDTLProtocol
				.getPartitionTransaction(JDBCPartitionTransactionUtil
						.getDBTransactionSignature(url, info, conn), "SQL");
		
		synchronized (appPartitionTransactionProtocol) {
			JsonObject sqlLogEntity = appPartitionTransactionProtocol.getInfo();
			if(sqlLogEntity.has("SQLS")){
				sqlLogs = sqlLogEntity.getAsJsonArray("SQLS");
			}else{
				sqlLogs = new JsonArray();
				sqlLogEntity.add("SQLS", sqlLogs);
			}
			if(!StringUtil.isBlank(sql)){
				constructSql = new SQLEntity();
				constructSql.setSql(STMT_TYPE, "DtSupportedPreparedStatement", sql);
			}
		}
	}
	
	/**
	 * 为存在的construactSqlProperty添加值
	 * @param property
	 * @param value
	 */
	void addConstructSqlProperty(String property, Object value) {
		if(constructSql != null){
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
		entity.setSql(STMT_TYPE, "executeUpdate",sql);
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
		entity.setSql(STMT_TYPE, "execute",sql);
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
		entity.setSql(STMT_TYPE, "addBatch",sql);
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
		entity.setSql(STMT_TYPE, "executeUpdate",sql);
		entity.addProperty("autoGeneratedKeys", autoGeneratedKeys);
		return this.origin.executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "executeUpdate",sql);
		entity.addProperty("columnIndexes", columnIndexes);
		return this.origin.executeUpdate(sql, columnIndexes);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "executeUpdate",sql);
		entity.addProperty("columnNames", columnNames);
		return this.origin.executeUpdate(sql, columnNames);
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "execute",sql);
		entity.addProperty("autoGeneratedKeys", autoGeneratedKeys);
		return this.origin.execute(sql, autoGeneratedKeys);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "execute",sql);
		entity.addProperty("columnIndexes", columnIndexes);
		return this.origin.execute(sql, columnIndexes);
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		SQLEntity entity = new SQLEntity(this.sqlLogs);
		entity.setSql(STMT_TYPE, "execute",sql);
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

	@Override
	public ResultSet executeQuery() throws SQLException {
		constructSql.append2Logs(sqlLogs);
		return this.origin.executeQuery();
	}

	@Override
	public int executeUpdate() throws SQLException {
		constructSql.append2Logs(sqlLogs);
		return this.origin.executeUpdate();
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(sqlType), "setNull");
		this.origin.setNull(parameterIndex, sqlType);
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setBoolean");
		this.origin.setBoolean(parameterIndex, x);
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setByte");
		this.origin.setByte(parameterIndex, x);
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setShort");
		this.origin.setShort(parameterIndex, x);
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setInt");
		this.origin.setInt(parameterIndex, x);
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setLong");
		this.origin.setLong(parameterIndex, x);
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setFloat");
		this.origin.setFloat(parameterIndex, x);
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setDouble");
		this.origin.setDouble(parameterIndex, x);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setBigDecimal");
		this.origin.setBigDecimal(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, x, "setString");
		this.origin.setString(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		try {
			constructSql.addSQLParams(parameterIndex, new String(x,"utf-8"), "setBytes");
		} catch (UnsupportedEncodingException e) {
			throw new SQLException(e);
		}
		this.origin.setBytes(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, TimeUtil.format(x), "setDate");
		this.origin.setDate(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, TimeUtil.format(x), "setTime");
		this.origin.setTime(parameterIndex, x);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, TimeUtil.format(x), "setTimestamp");
		this.origin.setTimestamp(parameterIndex, x);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setAsciiStream");
		this.origin.setAsciiStream(parameterIndex, x, length);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setUnicodeStream");
		this.origin.setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setBinaryStream");
		this.origin.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void clearParameters() throws SQLException {
		this.origin.clearParameters();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setObject");
		this.origin.setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setObject");
		this.origin.setObject(parameterIndex, x);
	}

	@Override
	public boolean execute() throws SQLException {
		constructSql.append2Logs(sqlLogs);		
		return this.origin.execute();
	}

	@Override
	public void addBatch() throws SQLException {
		constructSql.append2Logs(sqlLogs);
		this.origin.addBatch();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setCharacterStream");
		this.origin.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setRef");
		this.origin.setRef(parameterIndex, x);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setBlob");
		this.origin.setBlob(parameterIndex, x);
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setClob");
		this.origin.setClob(parameterIndex, x);
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setArray");
		this.origin.setArray(parameterIndex, x);
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return this.origin.getMetaData();
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal)
			throws SQLException {
		cal.setTime(x);
		constructSql.addSQLParams(parameterIndex, TimeUtil.format(cal), "setDate");
		this.origin.setDate(parameterIndex, x, cal);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal)
			throws SQLException {
		cal.setTime(x);
		constructSql.addSQLParams(parameterIndex, TimeUtil.format(cal), "setTime");
		this.origin.setTime(parameterIndex, x, cal);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
			throws SQLException {
		cal.setTime(x);
		constructSql.addSQLParams(parameterIndex, TimeUtil.format(cal), "setTimestamp");
		this.origin.setTimestamp(parameterIndex, x, cal);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(sqlType), "setNull");
		this.origin.setNull(parameterIndex, sqlType, typeName);
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setURL");
		this.origin.setURL(parameterIndex, x);
	}
	
	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return this.origin.getParameterMetaData();
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		constructSql.addSQLParams(parameterIndex, String.valueOf(x), "setRowId");
		this.origin.setRowId(parameterIndex, x);
	}

	@Override
	public void setNString(int parameterIndex, String value)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, value, "setNString");
		this.origin.setNString(parameterIndex, value);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value,
			long length) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setNCharacterStream");
		this.origin.setNCharacterStream(parameterIndex, value, length);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setNClob");
		this.origin.setNClob(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setClob");
		this.origin.setClob(parameterIndex, reader, length);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setBlob");
		this.origin.setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setNClob");
		this.origin.setNClob(parameterIndex, reader, length);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setSQLXML");
		this.origin.setSQLXML(parameterIndex, xmlObject);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scaleOrLength) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setObject");
		this.origin.setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setAsciiStream");
		this.origin.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setBinaryStream");
		this.origin.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader,
			long length) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setCharacterStream");
		this.origin.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setAsciiStream");
		this.origin.setAsciiStream(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setBinaryStream");
		this.origin.setBinaryStream(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setCharacterStream");
		this.origin.setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setNCharacterStream");
		this.origin.setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setClob");
		this.origin.setClob(parameterIndex, reader);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream)
			throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setBlob");
		this.origin.setBlob(parameterIndex, inputStream);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		constructSql.addSQLParams(parameterIndex, "unsupported value", "setNClob");
		this.origin.setNClob(parameterIndex, reader);
	}

}
