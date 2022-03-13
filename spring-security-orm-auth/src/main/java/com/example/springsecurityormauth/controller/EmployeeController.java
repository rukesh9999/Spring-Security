package com.example.springsecurityormauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springsecurityormauth.model.Employee;
import com.example.springsecurityormauth.service.EmployeeService;

@Controller
@RequestMapping("/hrms")
public class EmployeeController {
    
	@Autowired
	private EmployeeService EmployeeService;
	
	@GetMapping("/showregistrationrage")
	public ModelAndView showRegistrationPage()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("registration");
		return  model;
	}
	
	@PostMapping("/save")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee, ModelAndView model) 
	{
		String Message="";
		
		try {
		System.out.println("employee...."+employee);
		Long empId = EmployeeService.saveUser(employee);
		Message="Employee Saved "+empId+" Successfullyyyy....!!!";
		model.addObject("message", Message);
		model.setViewName("registration");
		}
		catch(Exception e)
		{
			Message="Employee Not Saved....!!!";
		}
		 return model;
	}
	
}
