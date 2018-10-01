package com.capgemini.bankappdbmvc.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import com.capgemini.bankappdbmvc.controller.CustomerController;

public class ApplicationTest {

	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class CustomerControllerTests {

		@Test
		public void contextLoads() {
			CustomerController customerController = new CustomerController();

			String result1 = customerController.getHomePage();
			assertEquals(result1, "home");


//			Model model;
//			String result2 = customerController.getLoginPage(model);
//			assertEquals(result2, "login");



			String result3 = customerController.getlogoutPage();
			assertEquals(result3, "logout");

//			String result4 = customerController.getProfileEditPage();
//			assertEquals(result4, "editProfile");
			

			
			String result5 = customerController.getPasswordEditPage();
			assertEquals(result5, "changePassword");
			
		}

	}
}
