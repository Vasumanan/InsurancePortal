package com.cg.customer.exceptions;
/**
 * This InsuranceNotFoundException is used create custom exception for insurance.
 * 
 * @author Vasumanan J
 *
 */
public class InsuranceNotFoundException extends RuntimeException {

	public InsuranceNotFoundException() {
		super();
	}

	public InsuranceNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
