package test.com.ai.paas.ipaas.mds.messageconsumer;

import java.util.ArrayList;
import java.util.List;

import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.IMsgProcessorHandler;

public class MsgProcessorHandlerImpl1 implements IMsgProcessorHandler {

	@Override
	public IMessageProcessor[] createInstances(int partitionNum) {
		List<IMessageProcessor> processors = new ArrayList<>();
		IMessageProcessor processor = null;
		for (int i = 0; i < partitionNum; i++) {
			processor = new MessageProcessorImpl1();
			processors.add(processor);
		}
		return processors.toArray(new IMessageProcessor[processors.size()]);
	}

}
