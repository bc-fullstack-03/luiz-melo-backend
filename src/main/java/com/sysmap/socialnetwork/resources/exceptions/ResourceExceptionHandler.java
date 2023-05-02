package com.sysmap.socialnetwork.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sysmap.socialnetwork.services.exception.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(NotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Resource not found";

		StandardError err = standardError(status, error, e, request);
		return ResponseEntity.status(status).body(err);
	}

	public StandardError standardError(HttpStatus status, String error, RuntimeException e,
			HttpServletRequest request) {
	
		var standardError = new StandardError();
		
		standardError.setTimestamp(Instant.now());
		standardError.setStatus(status.value());
		standardError.setError(error);
		standardError.setMessage(e.getMessage());
		standardError.setPath(request.getRequestURI());
		
		return standardError;
	}

}
