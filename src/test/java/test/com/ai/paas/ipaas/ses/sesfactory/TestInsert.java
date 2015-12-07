package test.com.ai.paas.ipaas.ses.sesfactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.paas.ipaas.search.service.ISearchClient;
import com.ai.paas.ipaas.search.service.SearchClientFactory;
import com.ai.paas.ipaas.search.vo.InsertFieldVo;
import com.ai.paas.ipaas.uac.vo.AuthDescriptor;
import com.google.gson.Gson;

public class TestInsert extends Thread {

	
	private int i ;
	private static final String AUTH_ADDR = "http://10.1.228.198:14821/iPaas-Auth/service/auth";
	private static AuthDescriptor ad = null;
	private static ISearchClient is = null;
	
	
	
	static{
		ad =  new AuthDescriptor(AUTH_ADDR, "B9178FB878834E7BA8CD02FB981C7F4D", "654321","SES001");
		try {
			is = SearchClientFactory.getSearchClient(ad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		
		List<String> dataList1 =  new ArrayList<String>();
		for(int i=0;i<100000;i++){
			InsertFieldVo fieldvo = new InsertFieldVo();
			
			Map map1 = new HashMap();
			map1.put("city_code", "12");
			map1.put("city_name", " CSS 样式和组件章节。");
//			map1.put("pay_name", "NNN");
//			Map <String, Object> map2 = new HashMap<String, Object>() ;
//			map2.put("input", "hello");
//			map1.put("suggest", map2);
//			map1.put("id", i);
			
			dataList1.add(new Gson().toJson(map1));
		}
		
		Long statTime = new Date().getTime();
		is.bulkInsertData(dataList1);
		Long endTime = new Date().getTime();
		
		System.out.println("thread"+i+"cost  time +++++++++++++++++++++"+(endTime - statTime));
		
	}
	
	
	public TestInsert(int i ){
		
		this.i = i;
	}
	
}
