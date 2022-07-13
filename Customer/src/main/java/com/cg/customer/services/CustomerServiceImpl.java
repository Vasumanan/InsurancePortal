package com.cg.customer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cg.customer.entity.Customer;
import com.cg.customer.entity.Insurance;
import com.cg.customer.exceptions.CustomerNotFoundException;
import com.cg.customer.exceptions.InsuranceNotFoundException;
import com.cg.customer.repository.CustomerRepository;
import com.cg.customer.repository.InsuranceRepository;
import com.cg.customer.services.vo.InsuranceResponse;
import com.cg.customer.services.vo.Payment;

import lombok.extern.slf4j.Slf4j;

/**
 * This CustomerServiceImpl will perform all the Customer related services
 * 
 * @author Vasumanan J
 *
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerServices {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	InsuranceRepository insuranceRepository;
	@Autowired
	RestTemplate restTemplate;
	

	// CRUD SERVICES

	@Override
	public Customer createCustomer(Customer customer) {
		log.info("Customer saved");
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		if (customerRepository.existsById(customerId))
			return customerRepository.findByCustomerId(customerId);
		log.error("Invalid Customer Id.");
		throw new CustomerNotFoundException("Please provide valid customer Id");
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		if (customerRepository.existsById(customer.getCustomerId())) {
			log.info("Customer updated.");
			return customerRepository.save(customer);
		}
		log.error("Invalid Customer Id.");
		throw new CustomerNotFoundException("Please provide valid customer Id :"+customer.getCustomerId());
	}

	@Override
	public String deleteCustomer(int customerId) {
		if (customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
			return "Customer Deleted!!!";
		}
		log.error("Invalid Customer Id.");
		throw new CustomerNotFoundException("Please provide valid customer Id");
	}

	// BUSINESS LOGIC SERVICES

	@Override
	public List<InsuranceResponse> getInsuranceDetails() {
		ResponseEntity<List<InsuranceResponse>> responseEntity = restTemplate.exchange(
				"http://INSURANCE-SERVICE/insurances/", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<InsuranceResponse>>() {
				});
		return responseEntity.getBody();
	}

	@Override
	public Insurance applyIsurance(int customerId, int insuranceId) {
		Insurance insurance;
		try {
			insurance = restTemplate.getForObject("http://INSURANCE-SERVICE/insurances/" + insuranceId, Insurance.class);
		} catch (Exception e) {
			log.error("Invalid Insurance Id.");
			throw new InsuranceNotFoundException("Please provide Valid Insurance Id");
		}
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (customer == null) {
			log.error("Invalid Customer Id.");
			throw new CustomerNotFoundException("Please provide valid customer Id");			
		}
		return insuranceRepository.save(createInsuranceForCustomer(customer, insurance));
	}

	@Override
	public Insurance createInsuranceForCustomer(Customer customer, Insurance insurance) {
		Insurance customerInsurance;
		Payment payment = restTemplate.getForObject("http://PAYMENT-SERVICE/payments/" + customer.getAccountNumber(),
				Payment.class);
		if (payment.getAmount() >= insurance.getInsurancePrice()) {
			customerInsurance = new Insurance(customer, insurance.getInsuranceId(), insurance.getInsuranceType(),
					insurance.getInsurancePrice(), "PENDING", "RECEIVED", insurance.getDurationInYears());
			payment.setAmount(payment.getAmount() - insurance.getInsurancePrice());
			restTemplate.put("http://PAYMENT-SERVICE/payments/", payment);
		} else
			customerInsurance = new Insurance(customer, insurance.getInsuranceId(), insurance.getInsuranceType(),
					insurance.getInsurancePrice(), "PENDING", "NOT RECEIVED", insurance.getDurationInYears());
		return customerInsurance;
	}

	@Override
	public List<Insurance> InsuranceDetailsOfCustomerId(int customerId) {
		if (customerRepository.existsById(customerId))
			return insuranceRepository.getInsuranceDetails(customerId);
		log.error("Invalid Customer Id.");
		throw new CustomerNotFoundException("Please provide valid customer Id");
	}

	@Override
	public List<Insurance> InsuranceDetailsOfAllCustomers() {
		return insuranceRepository.findAll();
	}

	@Override
	public String updateInsurance(Insurance customerInsurance) {
		if (insuranceRepository.existsById(customerInsurance.getInsuranceId())) {
			insuranceRepository.save(customerInsurance);
			return "Customer Insurance updated !!!";
		}
		log.error("Invalid Insurance Id.");
		throw new InsuranceNotFoundException("Please provide valid insurance Id");
	}
}
