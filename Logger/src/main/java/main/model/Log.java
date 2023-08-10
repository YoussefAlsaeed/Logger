package main.model;

import lombok.Data;

/**
 * The log entity
 */

@Data
public class Log {
    // Timestamp of the log entry
    private String timestamp;
    
    // User id of log entry
    private String userId;

    // Correlation ID associated with the log entry
	private String correlationId;

    // Log level indicating the severity of the log entry (e.g., INFO, WARNING, ERROR)
    private LogLevel logLevel;
    
    private Source source;

    // Destination of the log message 
    private String destination;

    // Message content of the log entry
    private String message;
    
    // Error code of the log entry
    private String errorCode;
    
    //Enum for the sources
    public enum Source {
    	SOURCE_A,
    	SOURCE_B,
    	SOURCE_C    	
    }
    
    // Enum for log levels
    public enum LogLevel {
        INFO,
        WARN,
        ERROR,
        DEBUG
    }
}
