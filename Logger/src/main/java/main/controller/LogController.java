package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.kafka.LogProducer;
import main.model.Log;
import main.util.LogUtil;

@RestController
public class LogController {
	
	// Inject the LogUtil to validate logs before sending them.
	@Autowired
	LogUtil logUtil;
	
	//Inject the log producer to send logs to Kafka topic
	@Autowired
	LogProducer logproducer;
	
	
	/**
	 * Endpoint for creating a new log entry.
	 *
	 * @param log The log object containing log information.
	 * @return ResponseEntity indicating the success or failure of log creation.
	 */
	@PostMapping("/logs")
    public ResponseEntity<String> createLogEntry(@RequestBody Log log) {

        if (logUtil.isValidLog(log)) 
        {
        	logproducer.sendMessage(logUtil.getTopicName(log.getSource()),log);
            return ResponseEntity.ok("Log entry created successfully");
        } 
        else 
        {
            return ResponseEntity.badRequest().body("Invalid log entry");
        }
    }
	

}
