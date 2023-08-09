package main.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import main.model.Log;


/**
 * Service class responsible for producing and sending log messages to a Kafka topic.
 */
@Service
public class LogProducer {
	
	private KafkaTemplate<String,Log> kafkaTemplate; 
	
	@Value("${kafka.topic.name}")
	private String topicName;

	
	public LogProducer(KafkaTemplate<String,Log> kafkaTemplate)
	{
		this.kafkaTemplate = kafkaTemplate;
	}
	
	/**
     * Sends a log message to the Kafka topic.
     *
     * @param log The log object containing log information.
     */
	public void sendMessage(Log log)
	{
	    kafkaTemplate.send(topicName, log);

	}

}
