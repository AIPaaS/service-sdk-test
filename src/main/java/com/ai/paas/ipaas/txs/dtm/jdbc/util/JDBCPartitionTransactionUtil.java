package com.ai.paas.ipaas.txs.dtm.jdbc.util;

import com.ai.paas.ipaas.txs.dtm.jdbc.DtSupportedConnection;
import com.google.gson.Gson;

/**
 * JDBC子事务工具类
 * 
 * @Title: JDBCPartitionTransactionUtil.java 
 * @author wusheng
 * @date 2015年3月16日 上午11:29:33 
 *
 */
public class JDBCPartitionTransactionUtil {
	public static String getDBTransactionSignature(String url,
			java.util.Properties info, DtSupportedConnection conn) {
		Gson gson = new Gson();
		return "tx=" + conn.toString() + "." +url + "||" + gson.toJson(info);
	}
}
