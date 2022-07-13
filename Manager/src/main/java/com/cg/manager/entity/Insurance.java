package com.cg.manager.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This Insurance contains fields for insurance.
 * 
 * @author Vasumanan J
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int insuranceId;
	private String insuranceType;
	private int insurancePrice;
	private int durationInYears;
}