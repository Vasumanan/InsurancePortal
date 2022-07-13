package com.cg.manager.service.vo;

import javax.persistence.CascadeType;

import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
	
	private int customerId;
	private String firstName;
	private String lastName;
	@OneToOne(cascade = CascadeType.ALL)
	private AddressResponse address;
	private String email;
	private String userName;
	private String password;
}
