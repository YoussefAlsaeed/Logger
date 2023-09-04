package main.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import main.controller.LogController;
import main.model.Log;



/**
 * This class provide service to log into suitable filenames using JSON format
 */

@Service
public class LogFileService {
	
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    private static final Marker ACCEPT_MARKER = MarkerFactory.getMarker("ACCEPT");
        
	
	public void handleLogFile(String filename, Log log)
	{
//        MDC.put("source", filename);
//        logger.info(ACCEPT_MARKER, log.toString());
//        MDC.remove("source");
        
        //To log without JSON format replace the below with the hashed logic
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String logAsString = objectMapper.writeValueAsString(log);

            MDC.put("source", filename);
            logger.info(ACCEPT_MARKER, "Log Object: {}", logAsString);
        } catch (Exception e) {
            logger.error("Error handling log file: {}", e.getMessage());
        } finally {
            MDC.remove("source");
        }
	}

}
