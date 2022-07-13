package com.cg.manager.service.vo;

import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponse {

	@ManyToOne
	private CustomerResponse customer;
	private int insuranceId;
	private String insuranceType;
	private int insurancePrice;
	private String insuranceApprovalStatus;
	private int durationInYears;
	private String paymentStatus;
}
