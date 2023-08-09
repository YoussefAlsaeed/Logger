package main.service;

import java.util.Objects;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import main.model.Log;

/**
 * Service class responsible for validating log entries.
 */

@Service
public class LogValidationService {
	
	
    /**
     * Checks whether a given log entry is valid.
     *
     * @param log The log entry to be validated.
     * @return true if the log is valid, false otherwise.
     */
	public boolean isValidLog(Log log) {
	    return Stream.of(
	            log.getCorrelationId(),
	            log.getLogLevel(),
	            log.getSource(),
	            log.getDestination(),
	            log.getMessage(),
	            log.getTimestamp()
	        )
	    	// Check if each value is not null and not empty after trimming	
	        .allMatch(value -> value != null && !value.trim().isEmpty());
	}

}
