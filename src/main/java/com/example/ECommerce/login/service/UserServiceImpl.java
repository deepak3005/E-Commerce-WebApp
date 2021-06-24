package com.example.ECommerce.login.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ECommerce.login.model.Role;
import com.example.ECommerce.login.model.User;
import com.example.ECommerce.login.repository.UserRepository;
import com.example.ECommerce.login.web.dto.UserRegistrationDto;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserServiceImpl implements UserService
{
	private User user;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) 
	{
		super();
		this.userRepository = userRepository;
	}

	String hardCodedPassKey = "abc123xyz456";
	
	String definedRole;
	
	String UserName;
	
	@Override
	public User save(UserRegistrationDto registrationDto) 
	{	
		if(registrationDto.getTxtPassKey()!=null&&registrationDto.getTxtPassKey().equals(hardCodedPassKey))
		{
			definedRole = "ROLE_ADMIN";
		}
		else
		{
			definedRole = "ROLE_USER";
		}
		
		User user = new User(
				registrationDto.getFirstName(), 
				registrationDto.getLastName(), 
				registrationDto.getEmail(), 
				passwordEncoder.encode(registrationDto.getPassword()), 
				registrationDto.getTxtPassKey(),
				Arrays.asList(new Role(definedRole)));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserName = username;
		User user = userRepository.findByEmail(UserName);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));	
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	public User getCurrentlyLoggedInUser()
	{
		User user = userRepository.findByEmail(UserName);
		return user;
	}

}
