package com.cg.manager.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cg.manager.entity.Insurance;
import com.cg.manager.entity.Manager;
import com.cg.manager.exception.CustomerNotFoundException;
import com.cg.manager.exception.ManagerNotFoundException;
import com.cg.manager.repository.ManagerRepository;
import com.cg.manager.service.vo.InsuranceResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * This ManagerServiceImpl will perform all the Manager related services
 * 
 * @author Vasumanan J
 *
 */
@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepository managerRepository;

	@Autowired
	RestTemplate restTemplate;

	// CRUD OPERATIONS

	@Override
	public Manager createManager(Manager manager) {
		log.info("Manager saved");
		return managerRepository.save(manager);
	}

	@Override
	public Manager findManagerById(int managerId) {
		if (managerRepository.existsById(managerId))
			return managerRepository.findByManagerId(managerId);
		log.error("Invalid Manager Id.");
		throw new ManagerNotFoundException("Please provide valid manager Id...");
	}

	@Override
	public Manager updateManager(Manager manager) {
		if (managerRepository.existsById(manager.getManagerId()))
			return managerRepository.save(manager);
		log.error("Invalid Manager Id.");
		throw new ManagerNotFoundException("Please provide valid manager Id...");
	}

	@Override
	public String deleteManager(int managerId) {
		if (managerRepository.existsById(managerId)) {
			managerRepository.deleteById(managerId);
			log.info("Manager deleted");
			return "Manager deleted!!!";
		}
		log.error("Invalid Manager Id.");
		throw new ManagerNotFoundException("Please provide valid manager Id...");
	}

	// BUSINESS LOGIC OPERATIONS

	@Override
	public List<InsuranceResponse> pendingRequest() {
		ResponseEntity<List<InsuranceResponse>> responseEntity = restTemplate.exchange(
				"http://CUSTOMER-SERVICE/customers/insurance", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<InsuranceResponse>>() {
				});
		List<InsuranceResponse> pendingApproval = responseEntity.getBody().stream()
				.filter(insurance -> insurance.getInsuranceApprovalStatus().equalsIgnoreCase("PENDING"))
				.collect(Collectors.toList());
		return pendingApproval;
	}

	@Override
	public List<Insurance> getAllInsurance() {
		ResponseEntity<List<Insurance>> responseEntity = restTemplate.exchange("http://INSURANCE-SERVICE/insurances",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Insurance>>() {
				});
		return responseEntity.getBody();
	}

	@Override
	public String approveOrRejectRequest(int customerId) {
		List<InsuranceResponse> customerInsurances;
		try {
			customerInsurances = getInsuranceDetailsOfCustomer(customerId);
		} catch (Exception e) {
			return e.getMessage();
		}
		customerInsurances.forEach(customerInsurance -> {
			if (customerInsurance.getPaymentStatus().equals("RECEIVED"))
				customerInsurance.setInsuranceApprovalStatus("Approved");
			else
				customerInsurance.setInsuranceApprovalStatus("Rejected");
			restTemplate.put("http://CUSTOMER-SERVICE/customers/insurance", customerInsurance);
		});
		return "Insurance Status Updated!!!";
	}

	@Override
	public List<InsuranceResponse> getInsuranceDetailsOfCustomer(int customerId) throws CustomerNotFoundException {
		ResponseEntity<List<InsuranceResponse>> responseEntity = restTemplate.exchange(
				"http://CUSTOMER-SERVICE/customers/" + customerId + "/insurance", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<InsuranceResponse>>() {
				});
		return responseEntity.getBody();
	}
}