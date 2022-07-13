package com.cg.insurance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.insurance.entity.Insurance;
import com.cg.insurance.exception.InsuranceNotFoundException;
import com.cg.insurance.repository.InsuranceRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * This InsuranceServiceImpl will perform all the Insurance related services
 * 
 * @author Vasumanan J
 *
 */
@Service
@Slf4j
public class InsuranceServiceImpl implements InsuranceService {
	@Autowired
	InsuranceRepository insuranceRepository;

	@Override
	public Insurance createInsurance(Insurance insurance) {
		log.info("Insurance saved.");
		return insuranceRepository.save(insurance);
	}

	@Override
	public List<Insurance> getAllInsurance() {
		return insuranceRepository.findAll();
	}

	@Override
	public Insurance updateInsurance(Insurance insurance) {
		if (insuranceRepository.existsById(insurance.getInsuranceId())) {
			log.info("Insurance updated");
			return insuranceRepository.save(insurance);			
		}
		log.error("Invalid Insurance Id.");;
		throw new InsuranceNotFoundException("Please provide valid insurance Id");
	}

	@Override
	public Insurance getInsuranceById(int insuranceId) {
		if (insuranceRepository.existsById(insuranceId))
			return insuranceRepository.findByInsuranceId(insuranceId);
		log.error("Invalid Insurance Id.");
		throw new InsuranceNotFoundException("Please provide valid insurance Id");
	}

	@Override
	public String deleteInsurance(int insuranceId) {
		if (insuranceRepository.existsById(insuranceId)) {
			insuranceRepository.deleteById(insuranceId);
			log.info("Insurance deleted.");
			return "Insurance Deleted!!!";
		}
		log.error("Invalid Insurance Id.");
		throw new InsuranceNotFoundException("Please provide valid insurance Id");
	}
}