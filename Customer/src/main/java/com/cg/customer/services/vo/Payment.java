package com.cg.customer.services.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	private String accountNumber;
	private String accountHolderName;
	private double amount;
}