//package main.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//
///**
// * This class is a configuration class that sets up the OpenAPI documentation for the API.
// * Uncomment for more swagger configurations
// */
//@Configuration
//@OpenAPIDefinition
//public class OpenApiConfig {
//	
//    @Bean
//    public OpenAPI customOpenAPI() {
//    	 return new OpenAPI()
//    	            .info(new Info()
//    	                .title("Log Processing API") // Set the title of the API documentation
//    	                .version("1.0")				 // Set the version of the API
//    	                .description("This API serves to accept log data from clients, process it, and dispatch it to the corresponding Kafka topic for further handling.")
//    	                // Provide a detailed description of the API's purpose
//    	            );
//    }
//
//
//}
