package com.cg.payment.exception;

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

	@ExceptionHandler(value = AccountNotFoundException.class)
	public ResponseEntity<Object> handleInsuranceNotFoundException(AccountNotFoundException exception,
			WebRequest request) {
		ExceptionResponse AccountNotFoundExceptionResponse = new ExceptionResponse(LocalDate.now(),
				exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(AccountNotFoundExceptionResponse, HttpStatus.NOT_FOUND);
	}
}