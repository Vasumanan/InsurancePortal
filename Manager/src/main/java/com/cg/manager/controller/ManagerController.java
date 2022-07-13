package com.cg.manager.controller;

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
import com.cg.manager.entity.Manager;
import com.cg.manager.service.ManagerService;
import com.cg.manager.service.MapValidationErrorService;


/**
 * This ManagerController is used to do request mapping to services.
 * 
 * @author Vasumanan J
 *
 */
@RestController
@RequestMapping("/managers")
public class ManagerController {
	@Autowired
	ManagerService managerService;
	@Autowired
	MapValidationErrorService mapValidationErrorService;

	// CRUD

	@PostMapping("")
	public ResponseEntity<?> createManager(@Valid @RequestBody Manager manager, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		return new ResponseEntity<>(managerService.createManager(manager), HttpStatus.OK);
	}

	@GetMapping("/{managerId}")
	public ResponseEntity<?> findManagerById(@PathVariable int managerId) {
		return new ResponseEntity<>(managerService.findManagerById(managerId), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<?> updateManager(@RequestBody Manager manager) {
		return new ResponseEntity<>(managerService.updateManager(manager), HttpStatus.OK);
	}

	@DeleteMapping("/{managerId}")
	public ResponseEntity<?> deleteManager(@PathVariable int managerId) {
		return new ResponseEntity<>(managerService.deleteManager(managerId), HttpStatus.OK);
	}

	// BUSINESS LOGIC

	@GetMapping("/pendingApproval")
	public ResponseEntity<?> pendingApproval() {
		return new ResponseEntity<>(managerService.pendingRequest(), HttpStatus.OK);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> approveOrRejectRequest(@PathVariable int customerId) {
		return new ResponseEntity<>(managerService.approveOrRejectRequest(customerId), HttpStatus.OK);
	}
}