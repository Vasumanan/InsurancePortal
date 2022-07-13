package com.cg.manager.exception;

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

	@ExceptionHandler(value = ManagerNotFoundException.class)
	public ResponseEntity<Object> handleInsuranceNotFoundException(ManagerNotFoundException exception,
			WebRequest request) {
		ExceptionResponse ManagerNotFoundExceptionResponse = new ExceptionResponse(LocalDate.now(),
				exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ManagerNotFoundExceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException exception,
			WebRequest request) {
		ExceptionResponse CustomerNotFoundExceptionResponse = new ExceptionResponse(LocalDate.now(),
				exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(CustomerNotFoundExceptionResponse, HttpStatus.NOT_FOUND);
	}
}