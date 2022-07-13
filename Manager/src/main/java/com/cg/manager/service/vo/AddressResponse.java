package com.cg.manager.service.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

	private int addressId;
	private String state;
	private String district;
	private String city;
	private String street;
	private String pincode;

}
