package com.cg.payment;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.payment.entity.Payment;
import com.cg.payment.exception.AccountNotFoundException;
import com.cg.payment.repository.PaymentRepository;
import com.cg.payment.service.PaymentService;

@SpringBootTest
public class PaymentServiceTest {
	private Payment paymentMock;

	@Autowired
	PaymentService paymentService;
	@MockBean
	PaymentRepository paymentRepository;

	@BeforeEach
	public void setUpMockData() {
		paymentMock = new Payment("8940823277", "Vasu", 3000.0);
	}

	@Test
	public void TestCreatePaymentForValidPayment() {
		when(paymentRepository.save(paymentMock)).thenReturn(paymentMock);
		Payment result = paymentService.createAccount(paymentMock);
		assertEquals(result.getAccountNumber(), paymentMock.getAccountNumber());
	}

	@Test
	public void TestGetPaymentByAccountNumberForValidPayment() {
		String accountNumber = "8940823277";
		when(paymentRepository.existsById(accountNumber)).thenReturn(true);
		when(paymentRepository.findByAccountNumber(accountNumber)).thenReturn(paymentMock);
		Payment result = paymentService.findByAccountNumber(accountNumber);
		assertEquals(result.getAccountHolderName(), paymentMock.getAccountHolderName());
	}

	@Test
	public void TestGetPaymentByAccountNumberForAccountNotFoundException() {
		String accountNumber = "8940823277";
		when(paymentRepository.existsById(accountNumber)).thenReturn(false);
		assertThrows(AccountNotFoundException.class, () -> paymentService.findByAccountNumber(accountNumber));
	}

	@Test
	public void TestDeletePaymentForValidResult() {
		String accountNumber = "8940823277";
		when(paymentRepository.existsById(accountNumber)).thenReturn(true);
		assertEquals("Account Deleted!!!", paymentService.deleteAccount(accountNumber));
	}

	@Test
	public void TestDeletePaymentForAccountNotFoundException() {
		String accountNumber = "8940823277";
		when(paymentRepository.existsById(accountNumber)).thenReturn(false);
		assertThrows(AccountNotFoundException.class, () -> paymentService.deleteAccount(accountNumber));
	}

	@Test
	public void TestUpdatePaymentForValidPayment() {
		when(paymentRepository.existsById(paymentMock.getAccountNumber())).thenReturn(true);
		paymentMock.setAmount(5000.0);
		when(paymentRepository.save(paymentMock)).thenReturn(paymentMock);
		assertEquals(paymentMock, paymentService.updateAccount(paymentMock));
	}

	@Test
	public void TestUpdatePaymentForAccountNotFoundException() {
		when(paymentRepository.existsById(paymentMock.getAccountNumber())).thenReturn(false);
		assertThrows(AccountNotFoundException.class, () -> paymentService.updateAccount(paymentMock));
	}
}
