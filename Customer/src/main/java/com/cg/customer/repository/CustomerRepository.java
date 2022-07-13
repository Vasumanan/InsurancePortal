package com.cg.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.customer.entity.Customer;

/**
 * This CustomerRepository is used to hold customer details.
 * 
 * @author Vasumanan J
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public Customer findByCustomerId(int customerId);
}
