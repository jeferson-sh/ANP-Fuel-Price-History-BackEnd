package com.learning.fuelpricehistory.exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public DataNotFoundException(Long id) {
        super("Could not find ID entity: " + id);
    }
}
