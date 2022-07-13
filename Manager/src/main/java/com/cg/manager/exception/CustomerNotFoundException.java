package com.cg.manager.exception;


/**
 * This CustomerNotFoundException is used create custom exception for customer.
 * 
 * @author Vasumanan J
 *
 */
public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
