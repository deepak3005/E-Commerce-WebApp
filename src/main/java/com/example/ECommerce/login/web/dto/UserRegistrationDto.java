package com.example.ECommerce.login.web.dto;

public class UserRegistrationDto 
{
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String txtPassKey;
	
	public UserRegistrationDto()
	{
		
	}
	
	public UserRegistrationDto(String firstName, String lastName, String email, String password, String txtPassKey) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.txtPassKey = txtPassKey;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTxtPassKey() {
		return txtPassKey;
	}

	public void setTxtPassKey(String txtPassKey) {
		this.txtPassKey = txtPassKey;
	}
	
	
}
