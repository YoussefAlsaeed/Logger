package main.util;

import java.util.EnumSet;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import main.model.Log;

@Component
public class LogUtil {
	
	/**
     * Checks whether a given log entry is valid.
     *
     * @param log The log entry to be validated.
     * @return true if the log is valid, false otherwise.
     */
	public boolean isValidLog(Log log) {
	    EnumSet<Log.LogLevel> validLogLevels = EnumSet.allOf(Log.LogLevel.class);
	    EnumSet<Log.Source> validSources = EnumSet.allOf(Log.Source.class);


	    return Stream.of(
	            log.getCorrelationId(),
	            log.getDestination(),
	            log.getMessage(),
	            log.getTimestamp()
	        )
	        .allMatch(value -> value != null && !value.trim().isEmpty())
	        && log.getLogLevel() != null
	        && validLogLevels.contains(log.getLogLevel())
	        && !log.getLogLevel().toString().isEmpty()  // Check if logLevel is not an empty string
	        && log.getSource() != null
	        && validSources.contains(log.getSource())
	        && !log.getSource().toString().isEmpty();
	}
	
	/**
	 * Gets the topic name using the log's source
	 * 
	 * @param logSource The source of the log
	 * @return topic name to produce logs to
	 */
	public String getTopicName(Log.Source logSource) {
        switch (logSource) {
            case SOURCE_A:
                return "sourceA-topic";
            case SOURCE_B:
                return "sourceB-topic";
            case SOURCE_C:
                return "sourceC-topic";
            default:
                throw new IllegalArgumentException("Undefined log source : " + logSource);
        }
    }

}
