package com.capgemini.bankappdbmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankappdbmvc.exceptions.CustomerNotFoundException;
import com.capgemini.bankappdbmvc.exceptions.FailedToUpdateException;
import com.capgemini.bankappdbmvc.exceptions.FailedToUpdatePassword;
import com.capgemini.bankappdbmvc.exceptions.FundTransferFailException;
import com.capgemini.bankappdbmvc.exceptions.LowBalanceException;

@ControllerAdvice
public class ExceptionController {
	// ConstraintViolationException
	@ExceptionHandler(value = ConstraintViolationException.class)
	public String constraintsError(HttpServletRequest request, ConstraintViolationException exception) {
		request.setAttribute("exception", exception.getMessage());
		return "errorPage";
	}

	//DataAccessExceptions
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public String loginError(HttpServletRequest request, CustomerNotFoundException exception) {
		request.setAttribute("exception", exception.getMessage());
		return "errorPage";
	}

	@ExceptionHandler(value = FailedToUpdateException.class)
	public String updateError(HttpServletRequest request, FailedToUpdateException exception) {
		request.setAttribute("exception", exception.getMessage());
		return "errorPage";
	}

	@ExceptionHandler(value = FailedToUpdatePassword.class)
	public String passwordError(HttpServletRequest request, FailedToUpdatePassword exception) {
		request.setAttribute("exception", exception.getMessage());
		return "errorPage";
	}
	
	//Service layer Exceptions
	@ExceptionHandler(value = LowBalanceException.class)
	public String withdrawError(HttpServletRequest request, LowBalanceException exception) {
		request.setAttribute("exception", exception.getMessage());
		return "errorPage";
	}
	
	@ExceptionHandler(value = FundTransferFailException.class)
	public String fundTransferError(HttpServletRequest request, FundTransferFailException exception) {
		request.setAttribute("exception", exception.getMessage());
		return "errorPage";
	}
}
