package com.spexcode.asset_management.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<String> handleEmptyResultDataAccess(Exception ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIlegalArgument(Exception ex){
        return ResponseEntity.badRequest().build();
    }
}
