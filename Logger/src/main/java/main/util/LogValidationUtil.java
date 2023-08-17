package main.util;

import java.util.EnumSet;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import main.model.Log;

@Component
public class LogValidationUtil {
	
	/**
     * Checks whether a given log entry is valid.
     *
     * @param log The log entry to be validated.
     * @return true if the log is valid, false otherwise.
     */
	public boolean isValidLog(@Valid Log log) {
		
        BindingResult bindingResult = new BeanPropertyBindingResult(log, "log");
		
		if (log.getLogLevel() == Log.LogLevel.ERROR || log.getLogLevel() == Log.LogLevel.DEBUG) 
		{
	        if (log.getErrorCode() == null || log.getErrorCode().isEmpty()) 
	        {
	        	System.err.println("Validation error: error code is empty");
	            return false;
	        }
	    }
		
		
		if (bindingResult.hasErrors()) 
	        {
				for (FieldError error : bindingResult.getFieldErrors()) {
					System.err.println("Validation error: " + error.getField() + " - " + error.getDefaultMessage());
				}
	            return false;
	        } 
		
		return true;
	}
	
}
