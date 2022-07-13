package com.cg.customer.exceptions;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * This CustomExceptionController is used handle all custom exceptions.
 * 
 * @author Vasumanan J
 *
 */
@RestControllerAdvice
public class CustomExceptionController {

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException exception,
			WebRequest request) {
		ExceptionResponse CustomerNotFoundExceptionResponse = new ExceptionResponse(LocalDate.now(),
				exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(CustomerNotFoundExceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InsuranceNotFoundException.class)
	public ResponseEntity<Object> handleInsuranceNotFoundException(InsuranceNotFoundException exception,
			WebRequest request) {
		ExceptionResponse InsuranceNotFoundExceptionResponse = new ExceptionResponse(LocalDate.now(),
				exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(InsuranceNotFoundExceptionResponse, HttpStatus.NOT_FOUND);
	}
}