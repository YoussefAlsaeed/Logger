package main.model;

import lombok.Data;

@Data
public class Log {
    private String timestamp;
	private String correlationId;
    private String logLevel;
    private String source;
    private String destination;
    private String message;
}
