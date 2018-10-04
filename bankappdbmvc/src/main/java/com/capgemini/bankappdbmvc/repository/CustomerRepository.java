package com.capgemini.bankappdbmvc.repository;

import com.capgemini.bankappdbmvc.entities.Customer;

public interface CustomerRepository {

	public Customer authenticate(Customer customer);
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
//	public Customer addCustomer(Customer customer);
//	boolean deleteCustomer(long customerId);
}
