package com.capgemini.simpleapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldcontroller {

	@RequestMapping("/")
	public String sayHello() {
		return "index";
	}
}
