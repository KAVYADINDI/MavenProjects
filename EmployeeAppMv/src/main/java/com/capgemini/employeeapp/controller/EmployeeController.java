package com.capgemini.employeeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.employeeapp.entities.Employee;
import com.capgemini.employeeapp.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/", method = RequestMethod.GET) // only "/" bcoz when we run the application we will get
																// localhost:8080/ -- it goes to the front controller

	public String getHomePage() {
		return "index";
	}

	@RequestMapping(value = "/addEmployeePage", method = RequestMethod.GET)
	public String getAddEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());
		return "addEmployeeForm";
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST) // Model similar to request scope
	public String addNewEmployee(@ModelAttribute Employee employee) {
		employeeService.addEmployee(employee);
		return "redirect:/findAllEmployeesPage";
		// or return getAllEmployeeDetails(model) -- specify Model model in
		// ModelAttribute
	}

	@RequestMapping(value = "/findAllEmployeesPage", method = RequestMethod.GET)
	public String finedAllemployeeDetails(Model model) {
		List<Employee> employees = employeeService.findAllemployees();
		model.addAttribute("allEmployees", employees);// similar to req.setAtt("a",a);
		return "allEmployees";
	}

	@RequestMapping(value = "/deleteEmployee/{employeeId}", method = RequestMethod.GET) // path variable: employeeId or
																						// emp
	public String deleteEmployee(@PathVariable int employeeId) {// @Pathvariable("emp") int employeeId or @PV int emp
		employeeService.deleteEmployee(employeeId);
		return "redirect:/findAllEmployeesPage";
	}

	@RequestMapping(value = "/editEmployeePage/{employeeId}", method = RequestMethod.GET)
	public String editEmployeePage(@PathVariable int employeeId, Model model) {
		Employee employee = employeeService.findEmployeeByID(employeeId);
		model.addAttribute("employee", employee);
		return "updateEmployeeForm";
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute Employee employee) {
		employeeService.updateEmployee(employee);
		return "redirect:/findAllEmployeesPage";
	}

	@RequestMapping(value = "/findEmployeePage", method = RequestMethod.GET)
	public String getEmployeeById(Model model) {
		model.addAttribute("employee", new Employee());
		return "searchForEmployeeForm";
	}

	@RequestMapping(value = "/searchForEmployee", method = RequestMethod.POST)
	public String getemployeeById(@ModelAttribute Employee employee,Model model) {
		Employee emp = employeeService.findEmployeeByID(employee.getEmployeeId());
		model.addAttribute("employee",emp);
		return "employeeById";
	}

}
