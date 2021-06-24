package com.example.ECommerce.login.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.ECommerce.login.model.User;
import com.example.ECommerce.login.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService
{
	User save(UserRegistrationDto registrationDto);
	
	User getCurrentlyLoggedInUser();

}
