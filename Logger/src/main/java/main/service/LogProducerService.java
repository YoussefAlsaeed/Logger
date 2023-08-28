package main.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
	private static List<Log.Source> logsources = new ArrayList<>(Arrays.asList(
			Log.Source.SOURCE_A,
			Log.Source.SOURCE_B,
			Log.Source.SOURCE_C
			));

			public static Iterator<Log.Source> logSourcesiterator = logsources.iterator();

	
	
	/**
     * Sends a log message to the Kafka topic.
     *
     * @param log The log object containing log information.
     */
	public void sendMessage(Log log)
	{
		if (!logSourcesiterator.hasNext()) {
			logSourcesiterator =logsources.iterator();
		}
		Log.Source source = logSourcesiterator.next();
		String topicName = source.toString();
        Message<Log> message = MessageBuilder
                .withPayload(log)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        System.out.println("IN LOG PRODUCER : " + topicName);
		kafkaTemplate.send(message);
	}



}
