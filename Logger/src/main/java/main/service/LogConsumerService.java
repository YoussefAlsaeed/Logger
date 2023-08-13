
package main.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import main.model.Log;
import main.util.LogValidationUtil;


@Service
@Validated
public class LogConsumerService {
	
	// Inject the LogUtil to validate logs before sending them.
	@Autowired
	LogValidationUtil logUtil;
	
	//Inject the log producer to send logs to Kafka topic
	@Autowired
	LogProducerService logProducer;
	
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
			logProducer.sendMessage(logUtil.getTopicName(log.getSource()),log);
		}
		else
		{
			System.err.println("log with correlation ID "+ log.getCorrelationId() +" is invalid");
		}
    }
}
