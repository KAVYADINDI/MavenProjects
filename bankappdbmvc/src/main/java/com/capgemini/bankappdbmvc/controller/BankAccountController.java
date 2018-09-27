package com.capgemini.bankappdbmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.capgemini.bankappdbmvc.service.BankAccountService;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String getHomePage() {
//		return "home";
//	}

	

}
