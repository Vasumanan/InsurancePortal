package com.cg.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.cg.payment.entity.Payment;
import com.cg.payment.exception.AccountNotFoundException;
import com.cg.payment.service.PaymentService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private PaymentService paymentService;
	@LocalServerPort
	private int port;
	private Payment paymentMock;

	@BeforeEach
	public void setUpMockData() {
		paymentMock = new Payment("8940823277", "Vasu", 3000.0);
	}

	@Test
	public void createAccountTestForValidCreate() {
		when(paymentService.createAccount(paymentMock)).thenReturn(paymentMock);
		ResponseEntity<Payment> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/payments",
				paymentMock, Payment.class);
		assertNotNull(postResponse);
		assertEquals(paymentMock, postResponse.getBody());
		assertThat(postResponse.getStatusCode().compareTo(HttpStatus.OK));
	}

	@Test
	public void findAccountByIdTestForValidAccount() {
		String accountNumber = "8940823277";
		when(paymentService.findByAccountNumber(accountNumber)).thenReturn(paymentMock);
		ResponseEntity<Payment> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/payments/{accountNumber}", Payment.class, accountNumber);
		assertNotNull(getResponse);
		assertEquals(paymentMock, getResponse.getBody());
		assertThat(getResponse.getStatusCode().compareTo(HttpStatus.OK));
	}

	@Test
	public void findAccountByIdTestForAccountNotFoundException() {
		String accountNumber = "8940823271";
		when(paymentService.findByAccountNumber(accountNumber)).thenThrow(new AccountNotFoundException());
		ResponseEntity<Payment> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/payments/{accountNumber}", Payment.class, accountNumber);
		assertThat(getResponse.getStatusCode().compareTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void updateAccountTestForValidAccount() {
		when(paymentService.updateAccount(paymentMock)).thenReturn(paymentMock);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Payment> entity = new HttpEntity<Payment>(paymentMock, headers);
		ResponseEntity<Payment> putResponse = restTemplate.exchange("http://localhost:" + port + "/payments",
				HttpMethod.PUT, entity, Payment.class);
		assertEquals(paymentMock, putResponse.getBody());
	}

	@Test
	public void updateAccountTestForAccountNotFoundException() {
		when(paymentService.updateAccount(paymentMock)).thenThrow(new AccountNotFoundException());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Payment> entity = new HttpEntity<Payment>(paymentMock, headers);
		ResponseEntity<Payment> putResponse = restTemplate.exchange("http://localhost:" + port + "/payments",
				HttpMethod.PUT, entity, Payment.class);
		assertThat(putResponse.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
	}

	@Test
	public void deleteAccountTestForValidMessage() {
		String accountNumber = "8940823277";
		when(paymentService.deleteAccount(accountNumber)).thenReturn("Payment Deleted!!!");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Payment> entity = new HttpEntity<Payment>(headers);
		ResponseEntity<String> deleteResponse = restTemplate.exchange(
				"http://localhost:" + port + "/payments/" + accountNumber, HttpMethod.DELETE, entity, String.class);
		assertThat(deleteResponse.getStatusCode().compareTo(HttpStatus.OK));
		assertEquals("Payment Deleted!!!", deleteResponse.getBody());
	}

	@Test
	public void deleteAccountTestForAccountNotFoundException() {
		String accountNumber = "8940823271";
		when(paymentService.deleteAccount(accountNumber)).thenThrow(new AccountNotFoundException());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Payment> entity = new HttpEntity<Payment>(headers);
		ResponseEntity<String> deleteResponse = restTemplate.exchange(
				"http://localhost:" + port + "/payments/" + accountNumber, HttpMethod.DELETE, entity, String.class);
		assertThat(deleteResponse.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
	}

}
