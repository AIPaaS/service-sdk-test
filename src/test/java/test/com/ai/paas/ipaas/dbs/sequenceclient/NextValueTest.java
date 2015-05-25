package test.com.ai.paas.ipaas.dbs.sequenceclient;

import test.com.ai.paas.ipaas.dbs.sequenceclient.base.SequenceClient;
import com.ai.paas.ipaas.dbs.sequence.ISequenceClient;
import org.junit.Before;
import org.junit.Test;

public class NextValueTest extends SequenceClient {
	private ISequenceClient iSequenceClient = null;

	@Before
	public void setUp() throws Exception {
		iSequenceClient = super.getClient();
	}

	/*** 正常情况测试 */
	@Test
	public void nextValue() {
		String str0 = "transaction_id_seq";
		iSequenceClient.nextValue(str0);
	}

	/*** null测试 */
	@Test(expected = IllegalArgumentException.class)
	public void nextValueFirstNull() {
		String str0 = null;
		iSequenceClient.nextValue(str0);
	}

	/*** 空对象 */
	@Test(expected = IllegalArgumentException.class)
	public void nextValueFirstBlank() {
		String str0 = "";
		iSequenceClient.nextValue(str0);
	}

}
