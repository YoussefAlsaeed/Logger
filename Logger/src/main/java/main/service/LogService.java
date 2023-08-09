package main.service;

import java.util.EnumSet;
import java.util.Objects;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import main.model.Log;

/**
 * Service class responsible for validating log entries.
 */

@Service
public class LogService {
	
	
    /**
     * Checks whether a given log entry is valid.
     *
     * @param log The log entry to be validated.
     * @return true if the log is valid, false otherwise.
     */
	public boolean isValidLog(Log log) {
	    EnumSet<Log.LogLevel> validLogLevels = EnumSet.allOf(Log.LogLevel.class);

	    return Stream.of(
	            log.getCorrelationId(),
	            log.getSource(),
	            log.getDestination(),
	            log.getMessage(),
	            log.getTimestamp()
	        )
	        .allMatch(value -> value != null && !value.trim().isEmpty())
	        && log.getLogLevel() != null
	        && validLogLevels.contains(log.getLogLevel())
	        && !log.getLogLevel().toString().isEmpty();  // Check if logLevel is not an empty string
	}
	
	public String getTopicName(Log.LogLevel logLevel) {
        switch (logLevel) {
            case INFO:
                return "info-topic";
            case WARN:
                return "warn-topic";
            case ERROR:
                return "error-topic";
            case DEBUG:
                return "debug-topic";
            default:
                throw new IllegalArgumentException("Unsupported log level: " + logLevel);
        }
    }


}
