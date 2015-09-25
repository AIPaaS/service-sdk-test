package test.com.ai.paas.ipaas.ses.sesinsert;

import test.com.ai.paas.ipaas.ses.sesfactory.TestInsert;


public class SesTest {

	
	public static  void   main(String[] args) {
		for(int i = 0 ;i<50 ;i++){
			TestInsert ti = new TestInsert(i);
			ti.start();
		}
	}
	
}
