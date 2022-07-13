package com.cg.insurance;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.insurance.entity.Insurance;
import com.cg.insurance.exception.InsuranceNotFoundException;
import com.cg.insurance.repository.InsuranceRepository;
import com.cg.insurance.service.InsuranceService;

@SpringBootTest
public class InsuranceServiceTest {
	private Insurance insuranceMock;
	@Autowired
	InsuranceService insuranceService;
	@MockBean
	InsuranceRepository insuranceRepository;

	@BeforeEach
	public void setUpMockData() {
		insuranceMock = new Insurance(1, "Health", 2000.0, 1);
	}

	@Test
	public void TestCreateInsuranceForValidInsurance() {
		when(insuranceRepository.save(insuranceMock)).thenReturn(insuranceMock);
		Insurance result = insuranceService.createInsurance(insuranceMock);
		assertEquals(result.getInsuranceId(), insuranceMock.getInsuranceId());
	}

	@Test
	public void TestGetInsuranceByIdForValidInsurance() {
		int insuranceId = 1;
		when(insuranceRepository.existsById(insuranceId)).thenReturn(true);
		when(insuranceRepository.findByInsuranceId(insuranceId)).thenReturn(insuranceMock);
		Insurance result = insuranceService.getInsuranceById(insuranceId);
		assertEquals(result.getInsuranceType(), insuranceMock.getInsuranceType());
	}

	@Test
	public void TestGetInsuranceByIdForInsuranceNotFoundException() {
		int insuranceId = 2;
		when(insuranceRepository.existsById(insuranceId)).thenReturn(false);
		assertThrows(InsuranceNotFoundException.class, () -> insuranceService.getInsuranceById(insuranceId));
	}

	@Test
	public void TestDeleteInsuranceForValidResult() {
		int insuranceId = 1;
		when(insuranceRepository.existsById(insuranceId)).thenReturn(true);
		assertEquals("Insurance Deleted!!!", insuranceService.deleteInsurance(insuranceId));
	}

	@Test
	public void TestDeleteInsuranceForInsuranceNotFoundException() {
		int insuranceId = 2;
		when(insuranceRepository.existsById(insuranceId)).thenReturn(false);
		assertThrows(InsuranceNotFoundException.class, () -> insuranceService.deleteInsurance(insuranceId));
	}

	@Test
	public void TestUpdateInsuranceForValidInsurance() {
		when(insuranceRepository.existsById(insuranceMock.getInsuranceId())).thenReturn(true);
		insuranceMock.setInsurancePrice(3000.0);
		when(insuranceRepository.save(insuranceMock)).thenReturn(insuranceMock);
		assertEquals(insuranceMock, insuranceService.updateInsurance(insuranceMock));
	}

	@Test
	public void TestUpdateInsuranceForInsuranceNotFoundException() {
		when(insuranceRepository.existsById(insuranceMock.getInsuranceId())).thenReturn(false);
		assertThrows(InsuranceNotFoundException.class, () -> insuranceService.updateInsurance(insuranceMock));
	}
}
