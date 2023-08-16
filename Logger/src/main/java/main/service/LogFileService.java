package main.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import main.controller.LogController;
import main.model.Log;


@Service
public class LogFileService {
	
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    private static final Marker ACCEPT_MARKER = MarkerFactory.getMarker("ACCEPT");
        
	
	public void handleLogFile(String filename, Log log)
	{
        MDC.put("source", filename);
        logger.info(ACCEPT_MARKER, log.toString());
        MDC.remove("source");
	}

}
