package com.capgemini.bankappdbmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankappdbmvc.exceptions.CustomerNotFoundException;
import com.capgemini.bankappdbmvc.service.impl.FailedToUpdateException;

@ControllerAdvice
public class ExceptionController {
	// ConstraintViolationException
	@ExceptionHandler(value = ConstraintViolationException.class)
	public String updateError(HttpServletRequest request, ConstraintViolationException exception) {
		request.setAttribute("exception", exception.getMessage());
		return "errorPage";
	}

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public String loginError(HttpServletRequest request, CustomerNotFoundException exception) {
		request.setAttribute("exception", exception.getMessage());
		return "errorPage";
	}

	@ExceptionHandler(value = FailedToUpdateException.class)
	public String updateError(HttpServletRequest request, FailedToUpdateException exception) {
		request.setAttribute("exception", exception.getMessage());
		System.out.println("jkkvbfjgrt");
		return "errorPage";
	}

}
