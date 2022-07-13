package com.cg.customer.entity;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This CustomerInsurance contains fields for insurance.
 * 
 * @author Vasumanan J
 *
 */
@Entity
@Table(name = "Insurance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

	@ManyToOne
	private Customer customer;
	@Id
	private int insuranceId;
	private String insuranceType;
	private int insurancePrice;
	private String insuranceApprovalStatus;
	private String paymentStatus;
	private int durationInYears;
}