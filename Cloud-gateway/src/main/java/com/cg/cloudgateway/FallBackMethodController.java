package com.cg.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
	
	@PostMapping("/customerServiceFallBack")
	public String customerServiceFallBackForPostMethod() {
		return "Customer Service is taking longer than expected.Please try again Later";
	}
	
	@GetMapping("/customerServiceFallBack")
	public String customerServiceFallBackForGetMethod() {
		return "Customer Service is taking longer than expected.Please try again Later";
	}
	@PostMapping("/insuranceServiceFallBack")
	public String insuranceServiceFallBackForPostMethod() {
		return "Insurance Service is taking longer than expected.Please try again Later";
	}
	
	@GetMapping("/insuranceServiceFallBack")
	public String insuranceServiceFallBackForGetMethod() {
		return "Insurance Service is taking longer than expected.Please try again Later";
	}
	
	@PostMapping("/paymentServiceFallBack")
	public String paymentServiceFallBackForPostMethod() {
		return "Payment Service is taking longer than expected.Please try again Later";
	}
	
	@GetMapping("/paymentServiceFallBack")
	public String paymentServiceFallBackForGetMethod() {
		return "Payment Service is taking longer than expected.Please try again Later";
	}
	
	@PostMapping("/managerServiceFallBack")
	public String managerServiceFallBackForPostMethod() {
		return "Manager Service is taking longer than expected.Please try again Later";
	}
	
	@GetMapping("/managerServiceFallBack")
	public String managerServiceFallBackForGetMethod() {
		return "Manager Service is taking longer than expected.Please try again Later";
	}
}
