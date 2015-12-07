package com.ai.paas.ipaas.txs.dtm.spring;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import com.ai.paas.ipaas.txs.dtm.DistributedTransactionContainer;
import com.ai.paas.ipaas.txs.dtm.DistributedTransactionController;
import com.ai.paas.ipaas.txs.dtm.exception.LocalTransactionException;
import com.ai.paas.ipaas.txs.dtm.jdbc.JDBCConnectionController;

/**
 * 扩展Spring数据源数据控制器<br/>
 * 默认加载JDBC DataSource中得连接到当前的分布式事务控制器<br/>
 * 
 * @Title: DistributedTransactionManager.java 
 * @author wusheng
 * @date 2015年3月23日 上午11:37:51 
 *
 */
public class DistributedTransactionManager extends DataSourceTransactionManager {
	private static final long serialVersionUID = -1307279471097132347L;

	/**
	 * 启动事务
	 */
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		/**
		 * 默认启动标准JDBC事务
		 */
		super.doBegin(transaction, definition);
		
		JdbcTransactionObjectSupport txObject = (JdbcTransactionObjectSupport) transaction;
		
		JDBCConnectionController connectionController;
		try {
			DistributedTransactionController.begin();
			
			connectionController = new JDBCConnectionController(
					txObject.getConnectionHolder().getConnection());
			DistributedTransactionContainer.append(
					JDBCConnectionController.class.getName(),
					connectionController);
		} catch (SQLException e) {
			throw new LocalTransactionException("DistributedTransactionManager begin error.", e);
		}
	}
	
	/**
	 * 提交事务<br/>
	 * 使用分布式事务管理提交机制，复写原有提交操作
	 */
	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		DistributedTransactionController.commit();
	}
	
	/**
	 * 回滚事务<br/>
	 * 使用分布式事务管理回滚机制，复写原有回滚操作
	 */
	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		DistributedTransactionController.rollback();
	}
	
	/**
	 * 完成事务性操作后，清除上下文<br/>
	 */
	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		/**
		 * 清除Spring默认的上下文
		 */
		super.doCleanupAfterCompletion(transaction);
		
		DistributedTransactionController.clear();
	}
}
