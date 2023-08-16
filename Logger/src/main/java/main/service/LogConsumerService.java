
package main.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
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
	
	//Inject the LogFileService to configure the log file name the log is typed into
	@Autowired
	LogFileService logFileService;

	
	/**
	 * Method that consumes logs from the client kafka topic to validate the log then send it to the LogRepo topic.
	 * 
	 * @param log The log object containing log information
	 * 
	 */

	@KafkaListener(topics = "${kafka.topic.client.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Log log) {
		
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String filename = log.getSource().toString() + "_" + time;
        
		if(logUtil.isValidLog(log))
		{
        	logFileService.handleLogFile(filename, log);
			logProducer.sendMessage(log);
		}
		else
		{
			System.err.println("log with correlation ID "+ log.getCorrelationId() +" is invalid");
		}
    }
}
