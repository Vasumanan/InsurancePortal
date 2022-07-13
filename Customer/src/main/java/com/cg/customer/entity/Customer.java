package com.cg.customer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Customer contains fields for customer.
 * 
 * @author Vasumanan J
 *
 */
@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	@NotEmpty(message = "Please provide your First Name")
	private String firstName;
	@NotEmpty(message = "Please provide your Last Name")
	private String lastName;
	@NotEmpty(message = "Please provide Account Number")
	private String accountNumber;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@Email(message = "Please provide valid emailId")
	private String email;
	@Length(min = 4, message = "Provide a username with Minimum length 4")
	private String userName;
	@Length(min = 5, max = 8)
	private String password;
}
