package com.learning.fuelpricehistory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenAccessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ForbiddenAccessException(String message) {
        super("Access denied");
    }
    
}