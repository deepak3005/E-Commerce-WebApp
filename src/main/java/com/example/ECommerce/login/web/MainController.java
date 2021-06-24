package com.example.ECommerce.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ECommerce.login.repository.UserRepository;
import com.example.ECommerce.login.service.UserServiceImpl;

@Controller
public class MainController 
{
	@GetMapping("/login")
	public String login() 
	{
		return "login";
	}
	
	@GetMapping("/adminHome")
	public String adminHome() 
	{
		return "adminHome";
	}
	
	@GetMapping("/userHome")
	public String userHome() 
	{
		return "redirect:/userDashboard";
	}
}
