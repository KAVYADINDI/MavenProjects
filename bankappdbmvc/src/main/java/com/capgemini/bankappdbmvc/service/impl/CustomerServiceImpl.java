package com.capgemini.bankappdbmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.capgemini.bankappdbmvc.entities.Customer;
import com.capgemini.bankappdbmvc.exceptions.CustomerNotFoundException;
import com.capgemini.bankappdbmvc.repository.CustomerRepository;
import com.capgemini.bankappdbmvc.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer authenticate(Customer customer) {
		try {
			return customerRepository.authenticate(customer);
		} catch (DataAccessException e) {

			CustomerNotFoundException exception = new CustomerNotFoundException("Customer not Found");
			exception.initCause(e);
			throw exception;
		}
	}

	@Override
	public Customer updateProfile(Customer customer) {
		try {
			return customerRepository.updateProfile(customer);
		} catch (DataAccessException e) {
			System.out.println("Service");
			FailedToUpdateException exception = new FailedToUpdateException("Failed to update the profile");
			exception.initCause(e);
			throw exception;
		}
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {

		return customerRepository.updatePassword(customer, oldPassword, newPassword);

	}

}
