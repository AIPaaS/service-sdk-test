package sdk.demo.util;

import com.ai.paas.ipaas.transaction.dtm.local.message.MessageCustomerTest;

public class MessageCustomerTask implements Runnable {

	@Override
	public void run() {
		new MessageCustomerTest().testMessage();
	}

}
