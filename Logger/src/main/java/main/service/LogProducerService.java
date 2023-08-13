package main.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class LogProducerService {
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate; 
	
	/**
     * Sends a log message to the Kafka topic.
     *
     * @param log The log object containing log information.
     */
	public void sendMessage(String topicName ,Log log)
	{
        Message<Log> message = MessageBuilder
                .withPayload(log)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
		kafkaTemplate.send(message);
	}

}
