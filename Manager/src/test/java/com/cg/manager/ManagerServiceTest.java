package com.cg.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.manager.entity.Manager;
import com.cg.manager.exception.ManagerNotFoundException;
import com.cg.manager.repository.ManagerRepository;
import com.cg.manager.service.ManagerService;

@SpringBootTest
public class ManagerServiceTest {
	private Manager managerMock;
	@Autowired
	ManagerService managerService;
	@MockBean
	ManagerRepository managerRepository;

	@BeforeEach
	public void setUpMockData() {
		managerMock = new Manager(1, "Vasumanan", "vasu@gmail.com", "VASU", "Vasu@123");
	}

	@Test
	public void TestCreateManagerForValidManager() {
		when(managerRepository.save(managerMock)).thenReturn(managerMock);
		System.out.println(managerMock.getManagerId());
		Manager result = managerService.createManager(managerMock);
		assertEquals(result.getManagerId(), managerMock.getManagerId());
	}

	@Test
	public void TestGetManagerByIdForValidManager() {
		int managerId = 1;
		when(managerRepository.existsById(managerId)).thenReturn(true);
		when(managerRepository.findByManagerId(managerId)).thenReturn(managerMock);
		Manager result = managerService.findManagerById(managerId);
		assertEquals(result.getName(), managerMock.getName());
	}

	@Test
	public void TestGetManagerByIdForManagerNotFoundException() {
		int managerId = 2;
		when(managerRepository.existsById(managerId)).thenReturn(false);
		assertThrows(ManagerNotFoundException.class, () -> managerService.findManagerById(managerId));
	}

	@Test
	public void TestDeleteManagerForValidResult() {
		int managerId = 1;
		when(managerRepository.existsById(managerId)).thenReturn(true);
		assertEquals("Manager deleted!!!", managerService.deleteManager(managerId));
	}

	@Test
	public void TestDeleteManagerForManagerNotFoundException() {
		int managerId = 2;
		when(managerRepository.existsById(managerId)).thenReturn(false);
		assertThrows(ManagerNotFoundException.class, () -> managerService.deleteManager(managerId));
	}

	@Test
	public void TestUpdateManagerForValidManager() {
		when(managerRepository.existsById(managerMock.getManagerId())).thenReturn(true);
		managerMock.setUserName("VASUMANAN");
		when(managerRepository.save(managerMock)).thenReturn(managerMock);
		assertEquals(managerMock.getUserName(), managerService.updateManager(managerMock).getUserName());
	}

	@Test
	public void TestUpdateManagerForManagerNotFoundException() {
		when(managerRepository.existsById(managerMock.getManagerId())).thenReturn(false);
		assertThrows(ManagerNotFoundException.class, () -> managerService.updateManager(managerMock));
	}
}
