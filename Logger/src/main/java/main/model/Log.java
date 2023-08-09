package main.model;

import lombok.Data;

/**
 * The log entity
 */

@Data
public class Log {
    // Timestamp of the log entry
    private String timestamp;

    // Correlation ID associated with the log entry
	private String correlationId;

    // Log level indicating the severity of the log entry (e.g., INFO, WARNING, ERROR)
    private LogLevel logLevel;

    // Source of the log message 
    private String source;

    // Destination of the log message 
    private String destination;

    // Message content of the log entry
    private String message;
    
    // Enum for log levels
    public enum LogLevel {
        INFO,
        WARN,
        ERROR,
        DEBUG
    }
}
