package com.spexcode.asset_management.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<String> handleEmptyResultDataAccess(Exception ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIlegalArgument(Exception ex){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<String> handleAccessDenied(Exception ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ACCESS DENIED");
    }

    // Override method to hide StackTrace
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>("METHOD_NOT_ALLOWED", HttpStatus.METHOD_NOT_ALLOWED);
	}
}
