package com.cg.manager.exception;


/**
 * This ManagerNotFoundException is used create custom exception for manager.
 * 
 * @author Vasumanan J
 *
 */
public class ManagerNotFoundException extends RuntimeException {

	public ManagerNotFoundException() {
		super();
	}

	public ManagerNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}