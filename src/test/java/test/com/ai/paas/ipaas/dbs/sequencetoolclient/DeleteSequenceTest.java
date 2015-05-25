package test.com.ai.paas.ipaas.dbs.sequencetoolclient;

import test.com.ai.paas.ipaas.dbs.sequencetoolclient.base.SequenceToolClient;
import com.ai.paas.ipaas.dbs.sequence.tool.ISequenceToolClient;
import org.junit.Before;
import org.junit.Test;

public class DeleteSequenceTest extends SequenceToolClient {
	private ISequenceToolClient iSequenceToolClient = null;

	@Before
	public void setUp() throws Exception {
		iSequenceToolClient = super.getClient();
	}

	/*** 正常情况测试 */
	@Test
	public void deleteSequence() {
		String str0 = "thenormaltest-str47145";
		iSequenceToolClient.deleteSequence(str0);
	}

	/*** null测试 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteSequenceFirstNull() {
		String str0 = null;
		iSequenceToolClient.deleteSequence(str0);
	}

	/*** 空对象 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteSequenceFirstBlank() {
		String str0 = "";
		iSequenceToolClient.deleteSequence(str0);
	}

}
