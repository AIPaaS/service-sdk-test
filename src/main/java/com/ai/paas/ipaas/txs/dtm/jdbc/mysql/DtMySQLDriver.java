package com.ai.paas.ipaas.txs.dtm.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import com.ai.paas.ipaas.txs.dtm.jdbc.DtSupportedConnection;
import com.mysql.jdbc.Driver;

public class DtMySQLDriver extends Driver {
	private static final String DRIVER_URL_PREFIX = "DT@MYSQL:";
	
	static{
		try {
			DriverManager.registerDriver(new DtMySQLDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DtMySQLDriver() throws SQLException {
		super();
	}

	@Override
	public Connection connect(String url, java.util.Properties info)
			throws SQLException {
		url = url.substring(DRIVER_URL_PREFIX.length());
		return new DtSupportedConnection(super.connect(url, info), url, info);
	}
	
	@Override
	public boolean acceptsURL(String url) throws SQLException{
		if(url.toUpperCase().startsWith(DRIVER_URL_PREFIX)){
			return true;
		}else{
			return false;
		}
	}

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
