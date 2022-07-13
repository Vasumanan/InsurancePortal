package com.cg.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Insurance contains fields for insurance.
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
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int insuranceId;
	@NotEmpty(message = "Please provide insurance type")
	private String insuranceType;
	private double insurancePrice;
	private int durationInYears;
}