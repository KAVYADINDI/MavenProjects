package com.capgemini.bankappdbmvc.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.bankappdbmvc.entities.Customer;
import com.capgemini.bankappdbmvc.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHome() {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@ModelAttribute Customer customer, HttpServletRequest request, HttpSession session) {
		Customer c = customerService.authenticate(customer);
		session = request.getSession();
		if (c != null) {
			session.setAttribute("customer", c);
			return "home";
		}
		return "redirect:/home"; // check
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getlogoutPage(Model model) {
		return "logout";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String getEditPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		model.addAttribute("customer", customer);
		System.out.println(customer);
		return "editProfilePage";
	}

	@RequestMapping(value = "/editProfile.do", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute Customer customer, HttpServletRequest request) {
		if (customerService.updateProfile(customer) != null)
			return "successEdit";
		return "error";
	}
}
