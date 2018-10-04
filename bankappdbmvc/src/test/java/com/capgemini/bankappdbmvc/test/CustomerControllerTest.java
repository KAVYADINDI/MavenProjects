package com.capgemini.bankappdbmvc.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.bankappdbmvc.controller.CustomerController;
import com.capgemini.bankappdbmvc.entities.BankAccount;
import com.capgemini.bankappdbmvc.entities.Customer;
import com.capgemini.bankappdbmvc.service.CustomerService;

public class CustomerControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testAuthenticateSuccess() throws Exception  {
		Customer customer = new Customer(1,null, "Asdfghjkl1",null,null,null,null);
		Customer customer1 = new Customer(1, "t", "Asdfghjkl1", "tom@gmail.com", "airoli mumbai",
				LocalDate.of(1999, 10, 07), new BankAccount(221700, "SAVINGS", 32000));

		when(customerService.authenticate(customer)).thenReturn(customer1);
		mockMvc.perform(post("/login.do").flashAttr("customer", customer))
				.andExpect(status().isOk())
				.andExpect(view().name("home"));// 200-success
	}
	
/*	@Test//constraint violation
	public void testAuthenticateFailed() throws Exception  {
		Customer customer = new Customer(1,null,null,null,null,null,null);
		Customer customer1 = new Customer();

		when(customerService.authenticate(customer)).thenReturn(customer1);
		mockMvc.perform(post("/login.do").flashAttr("customer", customer)).andExpect(view().name("home"))
				.andExpect(status().isOk());
	}*/
	
//	@Test
//	public void testLogout() throws Exception {
//		mockMvc.perform(get("/logout")).andExpect(view().name("logout")).andExpect(status().isOk());
//	}
}

