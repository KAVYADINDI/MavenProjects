package com.capgemini.bankappdbmvc.repository;

import org.springframework.stereotype.Repository;

import com.capgemini.bankappdbmvc.entities.Customer;

public interface CustomerRepository {

	public Customer authenticate(Customer customer);
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
}
