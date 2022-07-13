package com.cg.customer.services;

import java.util.List;
import com.cg.customer.entity.Customer;
import com.cg.customer.entity.Insurance;
import com.cg.customer.services.vo.InsuranceResponse;

/**
 * This CustomerServices will perform all the Customer related services
 * 
 * @author Vasumanan J
 *
 */
public interface CustomerServices {

	/**
	 * This createCustomer method will Save the customer in the Database.
	 * 
	 * @param customer it is the object of Customer which is to be added in
	 *                 database.
	 * @return This method will return Customer object if it is saved in database.
	 */
	public Customer createCustomer(Customer customer);

	/**
	 * This getCustomerById method is used to get the Customer by Id from the
	 * database.
	 * 
	 * @param customerId it is the Id of Customer which is to be read in Database.
	 * @return This method will return Customer object if it
	 *         found.CustomerNotFoundException in case there is no Customer in
	 *         database.
	 */
	public Customer getCustomerById(int customerId);

	/**
	 * This updateCustomer method is used to Update the Customer Which is already
	 * present in the database.
	 * 
	 * @param customer it is the object of Customer which is to be updated in
	 *                 database.
	 * @return This method will return Updated Customer object.
	 *         CustomerNotFoundException in case there is no Customer in database.
	 */
	public Customer updateCustomer(Customer customer);

	/**
	 * This deleteCustomer method is used to delete the Customer from the database.
	 * 
	 * @param customerId it is the Id of customer which is to be remove from
	 *                   database.
	 * @return This method will return message if deleted.CustomerNotFoundException
	 *         in case there is no Customer in database.
	 */
	public String deleteCustomer(int customerId);

	/**
	 * This getInsuranceDetails method is used to get insurance list from the
	 * database.
	 * 
	 * @return This method will return insurance list from the database.
	 */
	public List<InsuranceResponse> getInsuranceDetails();

	/**
	 * This applyIsurance method is used to save the insurance for customer in the
	 * database.
	 * 
	 * @param customerId  it is the Id of customer to whom you need to apply
	 *                    insurance.
	 * @param insuranceId it is the Id of insurance which you need to apply.
	 * @return This method will return details of customer and insurance from the
	 *         database.
	 */
	public Insurance applyIsurance(int customerId, int insuranceId);

	/**
	 * This createInsuranceForCustomer method is used to save the insurance and
	 * customer in the database.
	 * 
	 * @param customer  it is the object of Customer which is to be saved in
	 *                  database.
	 * @param insurance it is the Object of Insurance which is to be updated in
	 *                  database.
	 * @return This method will return details of customer and insurance from the
	 *         database.
	 */
	public Insurance createInsuranceForCustomer(Customer customer, Insurance insurance);

	/**
	 * This InsuranceDetailsOfCustomerId method is used to get insurance details for
	 * specific customer.
	 * 
	 * @param customerId it is the Id of customer to whom you need insurance
	 *                   details.
	 * @return This method will return list of insurances for a specific customer.
	 */
	public List<Insurance> InsuranceDetailsOfCustomerId(int customerId);

	/**
	 * This InsuranceDetailsOfAllCustomers method is used to get insurance details
	 * of all customers.
	 * 
	 * @return This method will return insurance details of all customers from the
	 *         database.
	 */
	public List<Insurance> InsuranceDetailsOfAllCustomers();

	/**
	 * This Insurance method is used to update insurance of the
	 * Customer in the database.
	 * 
	 * @param insurance it is the Insurance object which is to be updated in
	 *                          the database.
	 * @return This method will return message after updating.
	 */
	public String updateInsurance(Insurance insurance);

}
