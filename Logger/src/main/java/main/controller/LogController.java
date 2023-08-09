package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.kafka.LogProducer;
import main.model.Log;
import main.service.LogValidationService;

@RestController
public class LogController {
	
	@Autowired
	LogValidationService logValidationService;
	
	@Autowired
	LogProducer logproducer;
	
	@PostMapping("/logs")
    public ResponseEntity<String> createLogEntry(@RequestBody Log log) {

        if (logValidationService.isValidLog(log)) 
        {
        	logproducer.sendMessage(log);
            return ResponseEntity.ok("Log entry created successfully");
        } 
        else 
        {
            return ResponseEntity.badRequest().body("Invalid log entry");
        }
    }
}
