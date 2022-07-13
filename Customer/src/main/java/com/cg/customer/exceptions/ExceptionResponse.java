package com.cg.customer.exceptions;

import java.time.LocalDate;
/**
 * This ExceptionResponse is used create response for custom exceptions.
 * 
 * @author Vasumanan J
 *
 */
public class ExceptionResponse {

	private LocalDate date;
	private String message;
	private String details;

	public ExceptionResponse(LocalDate date, String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}

	public LocalDate getdate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
