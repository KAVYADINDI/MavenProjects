package com.capgemini.bankappdbmvc.service;

import com.capgemini.bankappdbmvc.entities.Customer;

public interface CustomerService {

	public Customer authenticate(Customer customer);
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) ;
//	public Customer getCustomer(long customerId) ;

}
