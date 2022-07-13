package com.cg.payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Payment contains fields for payment.
 * 
 * @author Vasumanan J
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Payment")
public class Payment {
	@Id
	@NotEmpty
	@Length(min = 10, max = 10, message = "Please provide 10 Digit Account Number")
	private String accountNumber;
	@NotEmpty(message = "Provide Account Holder Name")
	private String accountHolderName;
	private double amount;
}