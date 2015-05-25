package test.com.ai.paas.ipaas.dbs.sequencetoolclient;

import test.com.ai.paas.ipaas.dbs.sequencetoolclient.base.SequenceToolClient;
import com.ai.paas.ipaas.dbs.sequence.tool.ISequenceToolClient;
import org.junit.Before;
import org.junit.Test;

public class CreateSequenceTest extends SequenceToolClient {
	private ISequenceToolClient iSequenceToolClient = null;

	@Before
	public void setUp() throws Exception {
		iSequenceToolClient = super.getClient();
	}

	/*** 正常情况测试 */
	@Test
	public void createSequence() {
		String str0 = "thenormaltest-str61427";
		iSequenceToolClient.createSequence(str0);
	}

	/*** null测试 */
	@Test(expected = IllegalArgumentException.class)
	public void createSequenceFirstNull() {
		String str0 = null;
		iSequenceToolClient.createSequence(str0);
	}

	/*** 空对象 */
	@Test(expected = IllegalArgumentException.class)
	public void createSequenceFirstBlank() {
		String str0 = "";
		iSequenceToolClient.createSequence(str0);
	}

}
