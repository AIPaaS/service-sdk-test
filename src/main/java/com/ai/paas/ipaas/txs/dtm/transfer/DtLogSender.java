package com.ai.paas.ipaas.txs.dtm.transfer;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.paas.ipaas.mds.MsgSenderFactory;
import com.ai.paas.ipaas.txs.common.protocol.DTLProtocol;
import com.ai.paas.ipaas.txs.common.protocol.PartitionTransactionConfirmProtocol;
import com.ai.paas.ipaas.txs.common.protocol.ProtocolConstant;
import com.ai.paas.ipaas.txs.common.protocol.ProtocolSerializer;
import com.ai.paas.ipaas.txs.common.util.TimeCheck;
import com.ai.paas.ipaas.txs.dtm.config.LocalConfigCenter;
import com.ai.paas.ipaas.txs.dtm.exception.LocalTransactionTransferException;

/**
 * 分布式本地日志发送
 * 
 * @Title: DtLogSender.java
 * @author wusheng
 * @date 2015年3月20日 上午10:08:42
 *
 */
public class DtLogSender {
	private static Logger logger = LogManager.getLogger(DtLogSender.class);

	private static IMessageSender msgSender;

	private static Object lock = new Object();

	/**
	 * 初始化发送对象
	 * 
	 */
	private static void init() {
		if (msgSender == null) {
			synchronized (lock) {
				if (msgSender == null) {
					Properties kafaProps = LocalConfigCenter.instance()
							.getTxLogMsgKafkaProperties();
					msgSender = MsgSenderFactory.getClient(kafaProps, null,
							ProtocolConstant.DT_LOG_MSG_TOPIC);
				}
			}
		}
	}

	public static void sendDTL(DTLProtocol protocol) {
		init();

		if (logger.isDebugEnabled()) {
			logger.debug("ready to send DTL" + protocol);
		}
		try {
			TimeCheck checker = new TimeCheck();
			msgSender.send(ProtocolSerializer
					.encodingDTLProtocol(protocol), protocol.getTXID()
					.hashCode());
			logger.debug("send DTL.TXID=" + protocol.getTXID()
					+ " successfully.(" + checker.getTimeline() + "ms)");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("send DTL.TXID=" + protocol.getTXID()
						+ " failure.");
			}
			throw new LocalTransactionTransferException(
					"commit label error. send DTLProtocol failure", e);
		}
	}

	public static void sendPartitionTransactionConfirmProtocol(
			PartitionTransactionConfirmProtocol protocol) {
		init();

		if (logger.isDebugEnabled()) {
			logger.debug("ready to send confirm" + protocol);
		}
		try {
			/**
			 * confirm为非必要发送，如果失败则忽略
			 */
			TimeCheck checker = new TimeCheck();
			msgSender.send(ProtocolSerializer
					.encodingPartitionTransactionConfirmProtocol(protocol),
					protocol.getTXID().hashCode());
			logger.debug("send DTL.TXID.TransactionSignature="
					+ protocol.getTransactionSignature() + " successfully.("
					+ checker.getTimeline() + "ms)");
		} catch (Exception e) {
			logger.warn(
					"send DTL.TXID.TransactionSignature="
							+ protocol.getTransactionSignature() + " failure",
					e);
		}
	}
}
