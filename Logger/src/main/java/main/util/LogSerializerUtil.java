package main.util;


import org.springframework.kafka.support.serializer.JsonSerializer;
import main.model.Log;


/**
 * Custom serializer for serializing Log objects to JSON format.
 * Extends the JsonSerializer provided by Spring Kafka.
 */

public class LogSerializerUtil extends JsonSerializer<Log>{
	
	// This is an example of customizing where you can override how serializing works

	/*
	@Override
    public byte[] serialize(String topic, Log data) {
		return null;

    }
    */

}
