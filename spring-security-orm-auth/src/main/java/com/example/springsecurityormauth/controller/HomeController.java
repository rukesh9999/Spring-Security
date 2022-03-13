package com.example.springsecurityormauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hrms")
public class HomeController {
    
	@GetMapping("/home")
	public String showHome()
	{
		return "home";
	}
	
	@GetMapping("/welcome")
	public String showWelcome() {
		
		return "welcome";
	}
	
	@GetMapping("/employee")
	public String showEmployee()
	{
		return "employee";
	}
	
	@GetMapping("/admin")
	public String showAdmin()
	{
		return "admin";
	}
	
	@GetMapping("/student")
	public String showStudent()
	{
		return "student";
	}
	
	@GetMapping("/denied")
	public String showDenied()
	{
		return "denied";
	}
}
