package test.com.ai.paas.ipaas.mds.messageconsumer; 
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;

public class MessageProcessorImpl1 implements IMessageProcessor {

	@Override
	public void process(MessageAndMetadata message) throws Exception {
		if (null != message) {
			
			System.out.println("------Topic:" + message.getTopic() + ",key:"
					+ new String(message.getKey(), "UTF-8") + ",content:"
					+ new String(message.getMessage(), "UTF-8")+"id:"+message.getPartitionId());
		
		}
	}

}

