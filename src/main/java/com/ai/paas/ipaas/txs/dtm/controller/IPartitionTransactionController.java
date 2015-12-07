package com.ai.paas.ipaas.txs.dtm.controller;

/**
 * 本地分布式分区事务控制器
 * 
 * @Title: IPartitionTransactionController.java
 * @author wusheng
 * @date 2015年3月17日 下午1:23:31
 *
 */
public interface IPartitionTransactionController {
	public boolean isHighPriority();
	
	public void commit() throws Exception;

	public void rollback() throws Exception;

	public void clear() throws Exception;
}
