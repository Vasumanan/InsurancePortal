package com.cg.customer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Address contains fields for address.
 * 
 * @author Vasumanan J
 *
 */
@Entity
@Table(name = "Address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	private int addressId;
	private String street;
	private String city;
	private String district;
	private String state;
	private String pincode;
}
