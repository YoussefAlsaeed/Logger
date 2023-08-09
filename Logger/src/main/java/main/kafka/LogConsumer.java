
package main.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import main.model.Log;
import main.service.LogValidationService;


@Service
public class LogConsumer {
	
	@Autowired
	LogValidationService logValidationService;
	
	@Autowired
	LogProducer logproducer;

	@KafkaListener(topics = "${kafka.topic.client.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Log log) {
		if(logValidationService.isValidLog(log))
		{
			logproducer.sendMessage(log);
		}
		else
		{
			System.out.println("log with correlation ID "+ log.getCorrelationId() +" is invalid");
		}
    }
}

