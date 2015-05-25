package test.com.ai.paas.ipaas.dbs.sequencetoolclient;

import test.com.ai.paas.ipaas.dbs.sequencetoolclient.base.SequenceToolClient;
import com.ai.paas.ipaas.dbs.sequence.tool.ISequenceToolClient;
import org.junit.Before;
import org.junit.Test;

public class ModifySequenceTest extends SequenceToolClient {
	private ISequenceToolClient iSequenceToolClient = null;

	@Before
	public void setUp() throws Exception {
		iSequenceToolClient = super.getClient();
	}

	/*** 正常情况测试 */
	@Test
	public void modifySequence() {
		String str0 = "thenormaltest-str87541";
		long long1 = 10000000l;
		iSequenceToolClient.modifySequence(str0, long1);
	}

	/*** null测试 */
	@Test(expected = IllegalArgumentException.class)
	public void modifySequenceFirstNull() {
		String str0 = null;
		long long1 = 10000000l;
		iSequenceToolClient.modifySequence(str0, long1);
	}

	/*** null测试 */
	@Test(expected = IllegalArgumentException.class)
	public void modifySequenceSecondNull() {
		String str0 = "thenormaltest-str22043";
		long long1 = 0l;
		iSequenceToolClient.modifySequence(str0, long1);
	}

	/*** 空对象 */
	@Test(expected = IllegalArgumentException.class)
	public void modifySequenceFirstBlank() {
		String str0 = "";
		long long1 = 10000000l;
		iSequenceToolClient.modifySequence(str0, long1);
	}

	/*** 空对象 */
	@Test(expected = IllegalArgumentException.class)
	public void modifySequenceSecondBlank() {
		String str0 = "thenormaltest-str95010";
		long long1 = 0l;
		iSequenceToolClient.modifySequence(str0, long1);
	}

}
