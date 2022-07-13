package com.cg.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.payment.entity.Payment;
import com.cg.payment.exception.AccountNotFoundException;
import com.cg.payment.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * This PaymentServiceImpl will perform all the Payment related services
 * 
 * @author Vasumanan J
 *
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment createAccount(Payment payment) {
		log.info("Account saved.");
		return paymentRepository.save(payment);
	}

	@Override
	public Payment updateAccount(Payment payment) {
		if (paymentRepository.existsById(payment.getAccountNumber())) {
			log.info("Account Updated.");
			return paymentRepository.save(payment);
		}
		log.error("Invalid Account Number");
		throw new AccountNotFoundException("Please provide valid Account Number");
	}

	@Override
	public String deleteAccount(String accountNumber) {
		if (paymentRepository.existsById(accountNumber)) {
			paymentRepository.deleteById(accountNumber);
			log.info("Account deleted.");
			return "Account Deleted!!!";
		}
		log.error("Invalid Account Number");
		throw new AccountNotFoundException("Please provide valid Account Number");
	}

	@Override
	public Payment findByAccountNumber(String accountNumber) {
		if (paymentRepository.existsById(accountNumber))
			return paymentRepository.findByAccountNumber(accountNumber);
		log.error("Invalid Account Number");
		throw new AccountNotFoundException("Please provide valid Account Number");
	}
}