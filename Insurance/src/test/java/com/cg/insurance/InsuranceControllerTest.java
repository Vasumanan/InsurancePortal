package com.cg.insurance;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import com.cg.insurance.entity.Insurance;
import com.cg.insurance.exception.InsuranceNotFoundException;
import com.cg.insurance.service.InsuranceService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsuranceControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private InsuranceService insuranceService;
	@LocalServerPort
	private int port;
	private Insurance insuranceMock, insuranceMock1;

	@BeforeEach
	public void setUpMockData() {
		insuranceMock = new Insurance(1, "Health", 2000.0, 1);

	}

	@Test
	public void createInsuranceTestForValidCreate() {
		when(insuranceService.createInsurance(insuranceMock)).thenReturn(insuranceMock);
		ResponseEntity<Insurance> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/insurances",
				insuranceMock, Insurance.class);
		assertNotNull(postResponse);
		assertEquals(insuranceMock.getInsuranceType(), postResponse.getBody().getInsuranceType());
		assertThat(postResponse.getStatusCode().compareTo(HttpStatus.OK));
	}

	@Test
	public void findInsuranceByIdTestForValidInsurance() {
		int insuranceId = 1;
		when(insuranceService.getInsuranceById(insuranceId)).thenReturn(insuranceMock);
		ResponseEntity<Insurance> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/insurances/{insuranceId}", Insurance.class, insuranceId);
		assertNotNull(getResponse);
		assertEquals(insuranceMock, getResponse.getBody());
		assertThat(getResponse.getStatusCode().compareTo(HttpStatus.OK));
	}

	@Test
	public void findInsuranceByIdTestForInsuranceNotFoundException() {
		int insuranceId = 4;
		when(insuranceService.getInsuranceById(insuranceId)).thenThrow(new InsuranceNotFoundException());
		ResponseEntity<Insurance> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/insurances/{insuranceId}", Insurance.class, insuranceId);
		assertThat(getResponse.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
	}

	@Test
	public void updateInsurancerTestForValidInsurance() {
		when(insuranceService.updateInsurance(insuranceMock)).thenReturn(insuranceMock);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Insurance> entity = new HttpEntity<Insurance>(insuranceMock, headers);
		ResponseEntity<Insurance> putResponse = restTemplate.exchange("http://localhost:" + port + "/insurances",
				HttpMethod.PUT, entity, Insurance.class);
		assertEquals(insuranceMock, putResponse.getBody());
	}

	@Test
	public void updateInsuranceTestForInsuranceNotFoundException() {
		when(insuranceService.updateInsurance(insuranceMock)).thenThrow(new InsuranceNotFoundException());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Insurance> entity = new HttpEntity<Insurance>(insuranceMock, headers);
		ResponseEntity<Insurance> putResponse = restTemplate.exchange("http://localhost:" + port + "/insurances",
				HttpMethod.PUT, entity, Insurance.class);
		assertThat(putResponse.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
	}

	@Test
	public void deleteInsuranceTestForValidMessage() {
		int insuranceId = 1;
		when(insuranceService.deleteInsurance(insuranceId)).thenReturn("Insurance Deleted!!!");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Insurance> entity = new HttpEntity<Insurance>(headers);
		ResponseEntity<String> deleteResponse = restTemplate.exchange(
				"http://localhost:" + port + "/insurances/" + insuranceId, HttpMethod.DELETE, entity, String.class);
		assertThat(deleteResponse.getStatusCode().compareTo(HttpStatus.OK));
		assertEquals("Insurance Deleted!!!", deleteResponse.getBody());
	}

	@Test
	public void deleteInsuranceTestForInsuranceNotFoundException() {
		int insuranceId = 2;
		when(insuranceService.deleteInsurance(insuranceId)).thenThrow(new InsuranceNotFoundException());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Insurance> entity = new HttpEntity<Insurance>(headers);
		ResponseEntity<String> deleteResponse = restTemplate.exchange(
				"http://localhost:" + port + "/insurances/" + insuranceId, HttpMethod.DELETE, entity, String.class);
		assertThat(deleteResponse.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
	}

	@Test
	public void findAllInsuranceTestForInsuranceList() {
		insuranceMock1 = new Insurance(2, "Car", 4000.0, 1);
		List<Insurance> insuranceList = new ArrayList<>();
		insuranceList.add(insuranceMock);
		insuranceList.add(insuranceMock1);
		when(insuranceService.getAllInsurance()).thenReturn(insuranceList);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Insurance> entity = new HttpEntity<Insurance>(headers);
		ResponseEntity<List> getResponse = restTemplate.exchange("http://localhost:" + port + "/insurances/",
				HttpMethod.GET, entity, List.class);
		assertEquals(2, getResponse.getBody().size());
		assertThat(getResponse.getStatusCode().compareTo(HttpStatus.OK));
	}
}