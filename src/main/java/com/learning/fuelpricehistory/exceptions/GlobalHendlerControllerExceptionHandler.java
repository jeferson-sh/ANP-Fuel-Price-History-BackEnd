package com.learning.fuelpricehistory.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHendlerControllerExceptionHandler {
 
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
     
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handlerDataNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handlerAccessDenied(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlerDataIntegrityViolation(RuntimeException ex){
        return new ResponseEntity<>("The data could not be saved. Occurred a data integrity violation.", HttpStatus.NOT_ACCEPTABLE);
    }
}