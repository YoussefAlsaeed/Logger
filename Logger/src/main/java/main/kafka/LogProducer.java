package main.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import main.model.Log;


@Service
public class LogProducer {
	
	private KafkaTemplate<String,Log> kafkaTemplate; 
	
	@Value("${kafka.topic.name}")
	private String topicName;

	
	public LogProducer(KafkaTemplate<String,Log> kafkaTemplate)
	{
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(Log log)
	{
        Message<Log> message = MessageBuilder
                .withPayload(log)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
		kafkaTemplate.send(message);
	}

}
