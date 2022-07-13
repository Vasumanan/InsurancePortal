package com.cg.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.customer.entity.Address;
import com.cg.customer.entity.Customer;
import com.cg.customer.exceptions.CustomerNotFoundException;
import com.cg.customer.repository.CustomerRepository;
import com.cg.customer.services.CustomerServices;

@SpringBootTest
public class CustomerServiceTest {
	private Customer customerMock;
	private Address addressMock;
	@Autowired
	CustomerServices customerService;
	@MockBean
	CustomerRepository customerRepository;

	@BeforeEach
	public void setUpMockData() {
		addressMock = new Address(1, "No:5/1", "Chettipalayam", "Coimbatore", "TamilNadu", "641201");
		customerMock = new Customer(1, "Vasu", "J", "8940823277", addressMock, "vasu@gmail.com", "VASU", "Vasu@123");
	}

	@Test
	public void TestCreateCustomerForValid() {
		when(customerRepository.save(customerMock)).thenReturn(customerMock);
		System.out.println(customerMock.getCustomerId());
		Customer result = customerService.createCustomer(customerMock);
		assertEquals(result.getCustomerId(), customerMock.getCustomerId());
	}

	@Test
	public void TestGetCustomerByIdForValidCustomer() {
		int customerId = 1;
		when(customerRepository.existsById(customerId)).thenReturn(true);
		when(customerRepository.findByCustomerId(customerId)).thenReturn(customerMock);
		Customer result = customerService.getCustomerById(customerId);
		assertEquals(result.getFirstName(), customerMock.getFirstName());
	}

	@Test
	public void TestGetCustomerByIdForCustomerNotFoundException() {
		int customerId = 2;
		when(customerRepository.existsById(customerId)).thenReturn(false);
		assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customerId));
	}

	@Test
	public void TestDeleteCustomerForValidResult() {
		int customerId = 1;
		when(customerRepository.existsById(customerId)).thenReturn(true);
		assertEquals("Customer Deleted!!!", customerService.deleteCustomer(customerId));
	}

	@Test
	public void TestDeleteCustomerForCustomerNotFoundException() {
		int customerId = 2;
		when(customerRepository.existsById(customerId)).thenReturn(false);
		assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(customerId));
	}

	@Test
	public void TestUpdateCustomerForValidCustomer() {
		when(customerRepository.existsById(customerMock.getCustomerId())).thenReturn(true);
		customerMock.setUserName("VASUMANAN");
		when(customerRepository.save(customerMock)).thenReturn(customerMock);
		assertEquals(customerMock.getUserName(), customerService.updateCustomer(customerMock).getUserName());
	}

	@Test
	public void TestUpdateCustomerForCustomerNotFoundException() {
		when(customerRepository.existsById(customerMock.getCustomerId())).thenReturn(false);
		assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(customerMock));
	}
}
