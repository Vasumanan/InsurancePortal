package com.cg.manager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This Manager contains fields for manager.
 * 
 * @author Vasumanan J
 *
 */
@Entity
@Table(name = "Manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int managerId;
	@NotEmpty(message = "Please provide name")
	private String name;
	@Email(message = "Please provide valid EmailId")
	private String emailId;
	@NotEmpty(message = "Please provide UserName")
	private String userName;
	@NotEmpty(message = "Please provide Password")
	private String password;
}