package main.service;

import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import main.model.Log;

@Service
public class LogValidationService {

	public boolean isValidLog(Log log) {
	    return Stream.of(
	            log.getCorrelationId(),
	            log.getLogLevel(),
	            log.getSource(),
	            log.getDestination(),
	            log.getMessage(),
	            log.getTimestamp()
	        )
	        .allMatch(value -> value != null && !value.trim().isEmpty());
	}

}
