package com.cg.customer.exceptions;

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
