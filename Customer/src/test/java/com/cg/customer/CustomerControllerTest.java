package com.cg.customer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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
import com.cg.customer.entity.Address;
import com.cg.customer.entity.Customer;
import com.cg.customer.exceptions.CustomerNotFoundException;
import com.cg.customer.services.CustomerServices;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private CustomerServices customerService;
	@LocalServerPort
	private int port;
	private Customer customerMock1;
	private Address addressMock1;

	@BeforeEach
	public void setUpMockData() {
		addressMock1 = new Address(1, "No:5/1", "Chettipalayam", "Coimbatore", "TamilNadu", "641201");
		customerMock1 = new Customer(1, "Vasu", "J", "8940823277", addressMock1, "vasu@gmail.com", "VASU", "Vasu@123");
	}

	@Test
	public void createCustomerTestForValidCreate() {
		when(customerService.createCustomer(customerMock1)).thenReturn(customerMock1);
		ResponseEntity<Customer> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/customers",
				customerMock1, Customer.class);
		assertNotNull(postResponse);
		assertEquals(customerMock1, postResponse.getBody());
		assertThat(postResponse.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void findCustomerByIdTestForValidCustomer() {
		int customerId = 1;
		when(customerService.getCustomerById(customerId)).thenReturn(customerMock1);
		ResponseEntity<Customer> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/customers/{customerId}", Customer.class, customerId);
		assertNotNull(getResponse);
		assertEquals(customerMock1, getResponse.getBody());
		assertThat(getResponse.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void findCustomerByIdTestForCustomerNotFoundException() {
		int customerId = 4;
		when(customerService.getCustomerById(customerId)).thenThrow(new CustomerNotFoundException());
		ResponseEntity<Customer> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/customers/{customerId}", Customer.class, customerId);
		assertThat(getResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

	@Test
	public void updateCustomerTestForValidCustomer() {
		when(customerService.updateCustomer(customerMock1)).thenReturn(customerMock1);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(customerMock1, headers);
		ResponseEntity<Customer> putResponse = restTemplate.exchange("http://localhost:" + port + "/customers",
				HttpMethod.PUT, entity, Customer.class);
		assertEquals(customerMock1, putResponse.getBody());
	}

	@Test
	public void updateCustomerTestForCustomerNotFoundException() {
		when(customerService.updateCustomer(customerMock1)).thenThrow(new CustomerNotFoundException());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(customerMock1, headers);
		ResponseEntity<Customer> putResponse = restTemplate.exchange("http://localhost:" + port + "/customers",
				HttpMethod.PUT, entity, Customer.class);
		assertThat(putResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

	@Test
	public void deleteCustomerTestForValidMessage() {
		int customerId = 1;
		when(customerService.deleteCustomer(customerId)).thenReturn("Customer Deleted");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(headers);
		ResponseEntity<String> deleteResponse = restTemplate.exchange(
				"http://localhost:" + port + "/customers/" + customerId, HttpMethod.DELETE, entity, String.class);
		assertThat(deleteResponse.getStatusCode(), is(HttpStatus.OK));
		assertEquals("Customer Deleted", deleteResponse.getBody());
	}

	@Test
	public void deleteCustomerTestForCustomerNotFoundException() {
		int customerId = 2;
		when(customerService.deleteCustomer(customerId)).thenThrow(new CustomerNotFoundException());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(headers);
		ResponseEntity<String> deleteResponse = restTemplate.exchange(
				"http://localhost:" + port + "/customers/" + customerId, HttpMethod.DELETE, entity, String.class);
		assertThat(deleteResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
}