package com.sysmap.socialnetwork.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sysmap.socialnetwork.services.exception.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(NotFoundException e, HttpServletRequest request) {
		var standardError = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Resource not found";

		standardErrorMethod(status, error, e, request, standardError);
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		var validationError = new ValidationError();
		String error = "Validation Exception";
		standardErrorMethod(status, error, e, request, validationError);
				
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(validationError);
	}
	
	public void standardErrorMethod(HttpStatus status, String error, Exception e,
			HttpServletRequest request, StandardError standardError) {		
		standardError.setTimestamp(Instant.now());
		standardError.setStatus(status.value());
		standardError.setError(error);
		standardError.setMessage(e.getMessage());
		standardError.setPath(request.getRequestURI());
	}
	

}
