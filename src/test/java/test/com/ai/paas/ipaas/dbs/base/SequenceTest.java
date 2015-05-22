package test.com.ai.paas.ipaas.dbs.base;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.ai.paas.ipaas.dbs.sequence.ISequenceClient;
import com.ai.paas.ipaas.dbs.sequence.SequenceFactory;
import com.ai.paas.ipaas.dbs.sequence.tool.ISequenceToolClient;
import com.ai.paas.ipaas.dbs.sequence.tool.SequenceToolFactory;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;

public class SequenceTest  {
	
	public static void main(String[] args) {
		SequenceTest sequenceTest = new SequenceTest();
		sequenceTest.toolTest();
	}
	public void  testStart() {
		AuthDescriptor auth = new AuthDescriptor();
		auth.setAuthAdress("http://10.1.228.198:14821/iPaas-Auth/service/check");
 		auth.setPassword("1234567");
 		auth.setServiceId("DBS009");
 		auth.setUserName("mapl_590@163.com");
		ISequenceClient sequenceService = null;
		sequenceService = SequenceFactory.getClient(auth);
		for(int i = 0 ;i < 100;i++) {
		System.out.println(sequenceService.nextValue("TF_CHL_AGENT$AGENT_ID$SEQ") + "==================" + (i+1) );
		}
	}
	
	
	public void  toolTest() {
		AuthDescriptor auth = new AuthDescriptor();
		auth.setAuthAdress("http://10.1.228.198:14821/iPaas-Auth/service/check");
 		auth.setPassword("1234567");
 		auth.setServiceId("DBS009");
 		auth.setUserName("mapl_590@163.com");
		
		ISequenceToolClient  sequenceToolClient = null;
		sequenceToolClient = SequenceToolFactory.getClient(auth);
		sequenceToolClient.createSequence("testSequence1");
		//sequenceToolClient.modifySequence("testSequence1", 23l);
		
		for(int i = 0 ;i < 80;i++) {
			System.out.println(sequenceToolClient.nextValue("testSequence1") + "==================" + (i+1));
		}
		
		sequenceToolClient.deleteSequence("testSequence1");
		
		sequenceToolClient = SequenceToolFactory.getClient(auth);
		
		for(int i = 0 ;i < 100;i++) {
			try{
				System.out.println(sequenceToolClient.nextValue("testSequence1") + "==================" + (i+1));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Map<String,String> sequenceMap = sequenceToolClient.getAllSequence();
		Iterator<Entry<String,String>> iterator = sequenceMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String,String> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
			
		}
		
	}

}
