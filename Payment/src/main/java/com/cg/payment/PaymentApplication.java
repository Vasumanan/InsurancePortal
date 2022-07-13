package com.cg.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * This PaymentApplication is used to run the application.
 * 
 * @author Vasumanan J
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
}