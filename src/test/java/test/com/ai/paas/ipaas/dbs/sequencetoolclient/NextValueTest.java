package test.com.ai.paas.ipaas.dbs.sequencetoolclient;

import test.com.ai.paas.ipaas.dbs.sequencetoolclient.base.SequenceToolClient;
import com.ai.paas.ipaas.dbs.sequence.tool.ISequenceToolClient;
import org.junit.Before;
import org.junit.Test;

public class NextValueTest extends SequenceToolClient {
	private ISequenceToolClient iSequenceToolClient = null;

	@Before
	public void setUp() throws Exception {
		iSequenceToolClient = super.getClient();
	}

	/*** 正常情况测试 */
	@Test
	public void nextValue() {
		String str0 = "thenormaltest-str30820";
		iSequenceToolClient.nextValue(str0);
	}

	/*** null测试 */
	@Test(expected = IllegalArgumentException.class)
	public void nextValueFirstNull() {
		String str0 = null;
		iSequenceToolClient.nextValue(str0);
	}

	/*** 空对象 */
	@Test(expected = IllegalArgumentException.class)
	public void nextValueFirstBlank() {
		String str0 = "";
		iSequenceToolClient.nextValue(str0);
	}

}
