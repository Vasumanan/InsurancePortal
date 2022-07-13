package com.cg.insurance.controller;

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
import com.cg.insurance.entity.Insurance;
import com.cg.insurance.service.InsuranceService;
import com.cg.insurance.service.MapValidationErrorService;

/**
 * This InsuranceController is used to do request mapping to services.
 * 
 * @author Vasumanan J
 *
 */
@RestController
@RequestMapping("/insurances")
public class InsuranceController {

	@Autowired
	InsuranceService insuranceService;
	@Autowired
	MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> saveInsurance(@Valid @RequestBody Insurance insurance, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		return new ResponseEntity<>(insuranceService.createInsurance(insurance), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<?> updateInsurance(@RequestBody Insurance insurance) {
		return new ResponseEntity<>(insuranceService.updateInsurance(insurance), HttpStatus.OK);
	}

	@GetMapping("/{insuranceId}")
	public ResponseEntity<?> getInsuranceById(@PathVariable int insuranceId) {
		return new ResponseEntity<>(insuranceService.getInsuranceById(insuranceId), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> getAllInsurance() {
		return new ResponseEntity<>(insuranceService.getAllInsurance(), HttpStatus.OK);
	}

	@DeleteMapping("/{insuranceId}")
	public ResponseEntity<?> deleteInsurance(@PathVariable int insuranceId) {
		return new ResponseEntity<>(insuranceService.deleteInsurance(insuranceId), HttpStatus.OK);
	}
}
