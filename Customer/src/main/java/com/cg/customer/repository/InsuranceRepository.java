package com.cg.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.customer.entity.Insurance;

/**
 * This InsuranceRepository is used to hold insurance details.
 * 
 * @author Vasumanan J
 *
 */
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

	@Query("select insurance from Insurance insurance Where insurance.customer.customerId=:customerId")
	public List<Insurance> getInsuranceDetails(int customerId);

}
