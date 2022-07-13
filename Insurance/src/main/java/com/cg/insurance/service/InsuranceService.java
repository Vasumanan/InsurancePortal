package com.cg.insurance.service;

import java.util.List;

import com.cg.insurance.entity.Insurance;

/**
 * This InsuranceService will perform all the Insurance related services
 * 
 * @author Vasumanan J
 *
 */
public interface InsuranceService {

	/**
	 * This createInsurance method will Save the insurance in the Database.
	 * 
	 * @param insurance it is the object of Insurance which is to be added in
	 *                 database.
	 * @return This method will return insurance object if it is saved in database.
	 */
	public Insurance createInsurance(Insurance insurance);
	
	/**
	 * This getAllInsurance method is used to get insurance list from the
	 * database.
	 * 
	 * @return This method will return insurance list from the database.
	 */
	public List<Insurance> getAllInsurance();

	/**
	 * This updateInsurance method is used to update insurance in the database.
	 * 
	 * @param insurance it is the Insurance object which is to be updated in
	 *                          the database.
	 * @return This method will return updated insurance object from the database.
	 */
	public Insurance updateInsurance(Insurance insurance);
	
	/**
	 * This getInsuranceById method is used to get the Insurance by Id from the
	 * database.
	 * 
	 * @param insuranceId it is the Id of Insurance which is to be read in Database.
	 * @return This method will return Insurance object if it
	 *         found.InsuranceNotFoundException in case there is no Insurance in
	 *         database.
	 */
	public Insurance getInsuranceById(int insuranceId);

	/**
	 * This deleteInsurance method is used to delete the Insurance from the database.
	 * 
	 * @param insuranceId it is the Id of Insurance which is to be remove from
	 *                   database.
	 * @return This method will return message if deleted.InsuranceNotFoundException
	 *         in case there is no Insurance in database.
	 */
	public String deleteInsurance(int insuranceId);

}