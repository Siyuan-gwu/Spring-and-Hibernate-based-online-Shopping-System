package onlineShop.dao;

import onlineShop.model.Customer;

public interface CustomerDao {

	/**
	 * 
	 * @param customer
	 */
	boolean addCustomer(Customer customer);
	
	/**
	 * 
	 * @param username
	 * @return Customer
	 */
	Customer getCustomerByUserName(String username);
}
