
package main.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import main.model.Log;
import main.util.LogUtil;


@Service
public class LogConsumer {
	
	// Inject the LogUtil to validate logs before sending them.
	@Autowired
	LogUtil logUtil;
	
	//Inject the log producer to send logs to Kafka topic
	@Autowired
	LogProducer logproducer;
	
	/**
	 * Method that consumes logs from the client kafka topic to validate the log then send it to the LogRepo topic.
	 * 
	 * @param log The log object containing log information
	 * 
	 */

	@KafkaListener(topics = "${kafka.topic.client.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Log log) {
		if(logUtil.isValidLog(log))
		{
			logproducer.sendMessage(logUtil.getTopicName(log.getSource()),log);
		}
		else
		{
			System.out.println("log with correlation ID "+ log.getCorrelationId() +" is invalid");
		}
    }
}