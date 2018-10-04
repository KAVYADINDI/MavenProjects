package com.capgemini.bankappdbmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.capgemini.bankappdbmvc.entities.Customer;
import com.capgemini.bankappdbmvc.exceptions.CustomerNotFoundException;
import com.capgemini.bankappdbmvc.exceptions.FailedToUpdateException;
import com.capgemini.bankappdbmvc.exceptions.FailedToUpdatePassword;
import com.capgemini.bankappdbmvc.repository.CustomerRepository;
import com.capgemini.bankappdbmvc.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer authenticate(Customer customer) throws CustomerNotFoundException{
		try {
			return customerRepository.authenticate(customer);
		} catch (DataAccessException e) {

			CustomerNotFoundException exception = new CustomerNotFoundException("Customer not Found");
			exception.initCause(e);
			throw exception;
		}
	}

	@Override
	public Customer updateProfile(Customer customer) throws FailedToUpdateException {
		try {
			return customerRepository.updateProfile(customer);
		} catch (DataAccessException e) {
			FailedToUpdateException updationFailedException = new FailedToUpdateException("Failed to update the profile");
			updationFailedException.initCause(e);
			throw updationFailedException;
		}
	}

//	@Override
//	public Customer getCustomer(long customerId) throws SomeThingWentWrongException {
//
//		try {
//			return customerRepository.getCustomer(customerId);
//		} catch (DataAccessException e) {
//			SomeThingWentWrongException someThingWentWrongException = new SomeThingWentWrongException(
//					"Some thing went wrong");
//			someThingWentWrongException.initCause(e);
//			throw someThingWentWrongException;
//		}
//
//	}
	
	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws FailedToUpdatePassword {

		try {
		return customerRepository.updatePassword(customer, oldPassword, newPassword);
		}
		catch(DataAccessException e)
		{
		FailedToUpdatePassword	passwordChangeFailedException  = new FailedToUpdatePassword("Your old password might be incorrect");
		passwordChangeFailedException .initCause(e);
		throw passwordChangeFailedException ;
		}

	}

}
