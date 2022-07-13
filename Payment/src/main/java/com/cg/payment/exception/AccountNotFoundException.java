package com.cg.payment.exception;

/**
 * This AccountNotFoundException is used create custom exception for paymentAccount.
 * 
 * @author Vasumanan J
 *
 */
public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException() {
		super();
	}

	public AccountNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}