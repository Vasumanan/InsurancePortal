package com.cg.payment.service;

import com.cg.payment.entity.Payment;

public interface PaymentService {
	
	/**
	 * This createAccount method will Save the account in the Database.
	 * 
	 * @param payment it is the object of Payment which is to be added in database.
	 * @return This method will return payment object if it is saved in database.
	 */
	public Payment createAccount(Payment payment);

	/**
	 * This updateAccount method is used to update payment in the database.
	 * 
	 * @param payment it is the Payment object which is to be updated in the
	 *                database.
	 * @return This method will return updated payment object from the database.
	 */
	public Payment updateAccount(Payment payment);

	/**
	 * This deleteAccount method is used to delete the Account from the database.
	 * 
	 * @param accountNumber it is the Id of Account which is to be remove from database.
	 * @return This method will return message if deleted.AccountNotFoundException
	 *         in case there is no Account in database.
	 */
	public String deleteAccount(String accountNumber);

	/**
	 * This findByAccountNumber method is used to get the Manager by Id from the
	 * database.
	 * 
	 * @param accountNumber it is the Id of Account which is to be read in Database.
	 * @return This method will return Payment object if it
	 *         found.AccountNotFoundException in case there is no Payment in
	 *         database.
	 */
	public Payment findByAccountNumber(String accountNumber);

}
