package main.util;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import main.model.*;

/**
 * Custom deserializer for deserializing JSON data into Log objects.
 * Extends the JsonDeserializer provided by Spring Kafka.
 */

public class LogDeserializerUtil extends JsonDeserializer<Log>{
	
	// This is an example of customizing where you can override how deserializing works
	
	/*
    @Override
    public Log deserialize(String topic, byte[] data) {
        return null;
    }
    */


}
