package main.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import main.model.Log;
import main.service.LogFileService;
import main.service.LogProducerService;
import main.util.LogValidationUtil;

@Tag(name = "Log Controller")
@RestController
@Validated
@RequestMapping("/log")
public class LogController {
	
	// Inject the LogUtil to validate logs before sending them.
	@Autowired
	LogValidationUtil logUtil;
	
	//Inject the log producer to send logs to Kafka topic
	@Autowired
	LogProducerService logproducer;
	
	//Inject the LogFileService to configure the log file name the log is typed into
	@Autowired
	LogFileService logFileService;
	
	/**
	 * Endpoint for creating a new log entry.
	 *
	 * @param log The log object containing log information.
	 * @return ResponseEntity indicating the success or failure of log creation.
	 */
	@Operation(description = "POST api that allows log entry creation and then sent to matching kafka topic")
	@PostMapping("/rest-log")
	@SecurityRequirement(name="BearerAuth")
    public ResponseEntity<String> consumeLog(@RequestBody @Valid Log log) 
	{
		// Get log file name
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String filename = log.getSource().toString() + "_" + time;

        if (logUtil.isValidLog(log)) 
        {
        	logFileService.handleLogFile(filename, log);
        	logproducer.sendMessage(log);
            return ResponseEntity.ok("Log entry created successfully");
        } 
        else 
        {
            return ResponseEntity.badRequest().body("Invalid log entry");
        }
    }
	

}
