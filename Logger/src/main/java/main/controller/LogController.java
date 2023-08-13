package main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import main.model.Log;
import main.service.LogProducerService;
import main.util.LogValidationUtil;

@Tag(name = "Log Controller")
@RestController
@Validated
public class LogController {
	
	// Inject the LogUtil to validate logs before sending them.
	@Autowired
	LogValidationUtil logUtil;
	
	//Inject the log producer to send logs to Kafka topic
	@Autowired
	LogProducerService logproducer;
	
	
	/**
	 * Endpoint for creating a new log entry.
	 *
	 * @param log The log object containing log information.
	 * @return ResponseEntity indicating the success or failure of log creation.
	 */
	@Operation(description = "POST api that allows log entry creation and then sent to matching kafka topic")
	@PostMapping("/logs")
    public ResponseEntity<String> consumeLog(@RequestBody @Valid Log log) 
	{

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
