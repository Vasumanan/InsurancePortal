package com.cg.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * This InsuranceApplication is used to run the application.
 * 
 * @author Vasumanan J
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class InsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
	}
}