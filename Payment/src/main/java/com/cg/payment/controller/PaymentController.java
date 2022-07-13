package com.cg.payment.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.payment.entity.Payment;
import com.cg.payment.service.MapValidationErrorService;
import com.cg.payment.service.PaymentService;

/**
 * This PaymentController is used to do request mapping to services.
 * 
 * @author Vasumanan J
 *
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	@Autowired
	MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createAccount(@Valid @RequestBody Payment payment, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		return new ResponseEntity<>(paymentService.createAccount(payment), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<?> updateAccount(@RequestBody Payment payment) {
		return new ResponseEntity<>(paymentService.updateAccount(payment), HttpStatus.OK);
	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<?> findAccount(@PathVariable String accountNumber) {
		return new ResponseEntity<>(paymentService.findByAccountNumber(accountNumber), HttpStatus.OK);
	}

	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable String accountNumber) {
		return new ResponseEntity<>(paymentService.deleteAccount(accountNumber), HttpStatus.OK);
	}
}