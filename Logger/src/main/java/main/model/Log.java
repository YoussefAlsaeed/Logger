package main.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The log entity
 */

@Schema(description = "Log entry model ")
@Data

public class Log {
	// Timestamp of the log entry
    @NotBlank(message = "Timestamp cannot be blank")
    // Validate the time format
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Schema(
    	    example = "2023-08-10T15:30:00Z",
    	    description = "Timestamp of the log entry in the format: yyyy-MM-dd'T'HH:mm:ss'Z'"
    	)
    private String timestamp;

    // User id of log entry
    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    // Correlation ID associated with the log entry
    @NotBlank(message = "Correlation Id cannot be blank")
    private String correlationId;

    // Log level indicating the severity of the log entry (e.g., INFO, WARNING, ERROR)
    @NotNull(message = "Log level cannot be null")
    private LogLevel logLevel;

    @NotNull(message = "Source cannot be null")
    private String source;

    // Destination of the log message
    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    // Message content of the log entry
    @NotBlank(message = "Message cannot be blank")
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
    	TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }
}
