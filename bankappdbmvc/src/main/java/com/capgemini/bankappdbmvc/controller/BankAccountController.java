package com.capgemini.bankappdbmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankappdbmvc.entities.Customer;
import com.capgemini.bankappdbmvc.exceptions.LowBalanceException;
import com.capgemini.bankappdbmvc.service.BankAccountService;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@RequestMapping(value = "/checkBalance.do", method = RequestMethod.GET)
	public String getBalancePage() {
		return "checkBalance";
	}

	@RequestMapping(value = "/transferAmount", method = RequestMethod.GET)
	public String getFundTransferPage() {
		return "transferAmount";
	}

	@RequestMapping(value = "/transferAmount.do", method = RequestMethod.POST)
	public String fundTransfer(HttpSession session, HttpServletRequest request, Model model,
			@RequestParam long fromAccount, @RequestParam long toAccount, @RequestParam double amount)
			throws LowBalanceException {
		Customer customer = (Customer) session.getAttribute("customer");
		if (bankAccountService.fundTransfer(fromAccount, toAccount, amount)) {
			session.setAttribute("customer", customer);
			return "successfullTransfer";
		}
		return "errorPage";
	}

}
