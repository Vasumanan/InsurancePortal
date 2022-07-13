package com.cg.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.insurance.entity.Insurance;

/**
 * This InsuranceRepository is used to hold insurance details.
 * 
 * @author Vasumanan J
 *
 */
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

	public Insurance findByInsuranceId(int insuranceId);
}
