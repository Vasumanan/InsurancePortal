package com.cg.manager;

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
import com.cg.manager.entity.Manager;
import com.cg.manager.exception.ManagerNotFoundException;
import com.cg.manager.service.ManagerService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private ManagerService managerService;
	@LocalServerPort
	private int port;
	private Manager managerMock;

	@BeforeEach
	public void setUpMockData() {
		managerMock = new Manager(1, "Vasumanan", "vasu@gmail.com", "VASU", "Vasu@123");
	}

	@Test
	public void createManagerTestForValidCreate() {
		when(managerService.createManager(managerMock)).thenReturn(managerMock);
		ResponseEntity<Manager> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/managers",
				managerMock, Manager.class);
		assertNotNull(postResponse);
		assertEquals(managerMock, postResponse.getBody());
		assertThat(postResponse.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void findManagerByIdTestForValidManager() {
		int managerId = 1;
		when(managerService.findManagerById(managerId)).thenReturn(managerMock);
		ResponseEntity<Manager> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/managers/{managerId}", Manager.class, managerId);
		assertNotNull(getResponse);
		assertEquals(managerMock, getResponse.getBody());
		assertThat(getResponse.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void findManagerByIdTestForManagerNotFoundException() {
		int managerId = 4;
		when(managerService.findManagerById(managerId)).thenThrow(new ManagerNotFoundException("Provide Valid Id"));
		ResponseEntity<Manager> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/managers/{managerId}", Manager.class, managerId);
		assertThat(getResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));

	}

	@Test
	public void updateManagerTestForValidManager() {
		when(managerService.updateManager(managerMock)).thenReturn(managerMock);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Manager> entity = new HttpEntity<Manager>(managerMock, headers);
		ResponseEntity<Manager> putResponse = restTemplate.exchange("http://localhost:" + port + "/managers",
				HttpMethod.PUT, entity, Manager.class);
		assertEquals(managerMock, putResponse.getBody());
	}

	@Test
	public void updateManagerTestForManagerNotFoundException() {
		when(managerService.updateManager(managerMock)).thenThrow(new ManagerNotFoundException());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Manager> entity = new HttpEntity<Manager>(managerMock, headers);
		ResponseEntity<Manager> putResponse = restTemplate.exchange("http://localhost:" + port + "/managers",
				HttpMethod.PUT, entity, Manager.class);
		assertThat(putResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

	@Test
	public void deleteManagerTestForValidMessage() {
		int managerId = 1;
		when(managerService.deleteManager(managerId)).thenReturn("Manager Deleted!!!");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Manager> entity = new HttpEntity<Manager>(headers);
		ResponseEntity<String> deleteResponse = restTemplate.exchange(
				"http://localhost:" + port + "/managers/" + managerId, HttpMethod.DELETE, entity, String.class);
		assertThat(deleteResponse.getStatusCode(), is(HttpStatus.OK));
		assertEquals("Manager Deleted!!!", deleteResponse.getBody());
	}

	@Test
	public void deleteManagerTestForManagerNotFoundException() {
		int managerId = 2;
		when(managerService.deleteManager(managerId)).thenThrow(new ManagerNotFoundException());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Manager> entity = new HttpEntity<Manager>(headers);
		ResponseEntity<String> deleteResponse = restTemplate.exchange(
				"http://localhost:" + port + "/managers/" + managerId, HttpMethod.DELETE, entity, String.class);
		assertThat(deleteResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
}