package com.capgemini.bankappdbmvc.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
			@NotNull@RequestParam long fromAccount, @NotNull@RequestParam long toAccount,
			@NotNull@Min(1000) @Max(10000) @RequestParam double amount) throws LowBalanceException {

		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<Double>> violations = validator.validate(amount);

		Customer customer = (Customer) session.getAttribute("customer");
		if (violations.size() > 0)
			throw new ConstraintViolationException(violations);
		
		else if (bankAccountService.fundTransfer(fromAccount, toAccount, amount)) {
			session.setAttribute("customer", customer);
			return "successfullTransfer";
		}
		else {
			request.setAttribute("exception", "Check the transaction limits");
		return "errorPage";
	}
}
}
