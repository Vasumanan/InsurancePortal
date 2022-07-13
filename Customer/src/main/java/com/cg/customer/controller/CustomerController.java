package com.cg.customer.controller;

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
import com.cg.customer.entity.Customer;
import com.cg.customer.entity.Insurance;
import com.cg.customer.services.CustomerServices;
import com.cg.customer.services.MapValidationErrorService;

/**
 * This CustomerController is used to do request mapping to services.
 * 
 * @author Vasumanan J
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerServices customerService;
	@Autowired
	MapValidationErrorService mapValidationErrorService;

	// CRUD

	@PostMapping("")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<?> findCustomerById(@PathVariable int customerId) {
		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) {
		return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
	}

	// BUSINESS LOGIC

	@PostMapping("/{customerId}/insurance/{insuranceId}")
	public ResponseEntity<?> applyIsurance(@PathVariable int customerId, @PathVariable int insuranceId) {
		return new ResponseEntity<>(customerService.applyIsurance(customerId, insuranceId), HttpStatus.OK);
	}

	@GetMapping("/{customerId}/insurance")
	public ResponseEntity<?> findInsuranceOfCustomer(@PathVariable int customerId) {
		return new ResponseEntity<>(customerService.InsuranceDetailsOfCustomerId(customerId), HttpStatus.OK);
	}

	@PutMapping("/insurance")
	public ResponseEntity<?> updateInsurance(@RequestBody Insurance insurance) {
		return new ResponseEntity<>(customerService.updateInsurance(insurance), HttpStatus.OK);
	}

	@GetMapping("/insurance")
	public ResponseEntity<?> findInsuranceOfAllCustomers() {
		return new ResponseEntity<>(customerService.InsuranceDetailsOfAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findInsuranceDetails() {
		return new ResponseEntity<>(customerService.getInsuranceDetails(), HttpStatus.OK);
	}

}
