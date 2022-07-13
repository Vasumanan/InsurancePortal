package com.cg.manager.service;

import java.util.List;

import com.cg.manager.entity.Insurance;
import com.cg.manager.entity.Manager;
import com.cg.manager.exception.CustomerNotFoundException;
import com.cg.manager.service.vo.InsuranceResponse;

/**
 * This InsuranceService will perform all the Insurance related services
 * 
 * @author Vasumanan J
 *
 */
public interface ManagerService {

	/**
	 * This createManager method will Save the manager in the Database.
	 * 
	 * @param manager it is the object of Manager which is to be added in database.
	 * @return This method will return manager object if it is saved in database.
	 */
	public Manager createManager(Manager manager);

	/**
	 * This updateManager method is used to update manager in the database.
	 * 
	 * @param manager it is the Manager object which is to be updated in the
	 *                database.
	 * @return This method will return updated manager object from the database.
	 */
	public Manager updateManager(Manager manager);

	/**
	 * This findManagerById method is used to get the Manager by Id from the
	 * database.
	 * 
	 * @param managerId it is the Id of Manager which is to be read in Database.
	 * @return This method will return Manager object if it
	 *         found.ManagerNotFoundException in case there is no Manager in
	 *         database.
	 */
	public Manager findManagerById(int managerId);

	/**
	 * This deleteManager method is used to delete the Manager from the database.
	 * 
	 * @param managerId it is the Id of Manager which is to be remove from database.
	 * @return This method will return message if deleted.ManagerNotFoundException
	 *         in case there is no Manager in database.
	 */
	public String deleteManager(int managerId);

	/**
	 * This pendingRequest method is used to get approval pending list from the
	 * database.
	 * 
	 * @return This method will return approval pending list from the database.
	 */
	public List<InsuranceResponse> pendingRequest();

	/**
	 * This getAllInsurance method is used to get insurance list from the database.
	 * 
	 * @return This method will return insurance list from the database.
	 */
	public List<Insurance> getAllInsurance();

	/**
	 * This approveOrRejectRequest method is used to approve or reject the insurance
	 * request of the customer.
	 * 
	 * @param customerId it is the Id of Customer to whom you need to update the
	 *                   status.
	 * @return This method will return message after updating insurance status.
	 */
	public String approveOrRejectRequest(int customerId);

	/**
	 * This getInsuranceDetailsOfCustomer method is used to get the insurance
	 * details of the customer.
	 * 
	 * @param customerId it is the Id of Customer to whom to want the insurance
	 *                   details.
	 * @throws CustomerNotFoundException is thrown when customerId is invalid.
	 * @return This method will return list of insurance for a specific customer.
	 */
	public List<InsuranceResponse> getInsuranceDetailsOfCustomer(int customerId) throws CustomerNotFoundException;
}