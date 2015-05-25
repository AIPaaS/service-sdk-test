package test.com.ai.paas.ipaas.dbs.sequencetoolclient;

import test.com.ai.paas.ipaas.dbs.sequencetoolclient.base.SequenceToolClient;
import com.ai.paas.ipaas.dbs.sequence.tool.ISequenceToolClient;
import org.junit.Before;
import org.junit.Test;

public class GetAllSequenceTest extends SequenceToolClient {
	private ISequenceToolClient iSequenceToolClient = null;

	@Before
	public void setUp() throws Exception {
		iSequenceToolClient = super.getClient();
	}

	/*** 正常情况测试 */
	@Test
	public void getAllSequence() {
		iSequenceToolClient.getAllSequence();
	}

}
