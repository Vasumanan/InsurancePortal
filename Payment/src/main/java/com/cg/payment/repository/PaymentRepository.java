package com.cg.payment.repository;

import org.springframework.stereotype.Repository;
import com.cg.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This PaymentRepository is used to hold payment details.
 * 
 * @author Vasumanan J
 *
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

	public Payment findByAccountNumber(String accountNumber);
}
