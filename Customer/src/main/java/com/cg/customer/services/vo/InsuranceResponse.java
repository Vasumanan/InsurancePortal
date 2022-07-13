package com.cg.customer.services.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int insuranceId;
	private String insuranceType;
	private int insurancePrice;
	private int durationInYears;
}