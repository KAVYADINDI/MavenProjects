package com.capgemini.bankappdbmvc.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.bankappdbmvc.controller.CustomerController;
import com.capgemini.bankappdbmvc.entities.BankAccount;
import com.capgemini.bankappdbmvc.entities.Customer;
import com.capgemini.bankappdbmvc.repository.CustomerRepository;

public class TestClass {

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerRepository customerRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthenticate() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setCustomerPassword("asdfghjkl");

		Customer customer1 = new Customer(1,"t","asdfghjkl","tom@gmail.com","airoli mumbai",
				LocalDate.of(1999, 10, 07), new BankAccount(221700, "SAVINGS",3200));

		when(customerRepository.authenticate(customer)).thenReturn(customer1);
		assertEquals(customer1, customerRepository.authenticate(customer));

}
}

