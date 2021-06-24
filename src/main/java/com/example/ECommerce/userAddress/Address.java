package com.example.ECommerce.userAddress;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Address")
public class Address 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="RegisteredEmail")
	private String registeredEmail;
	@Column(name="Name")
	private String name;
	@Column(name="PhoneNumber")
	private int mobile;
	@Column(name="HouseNumber")
	private String houseNumber;
	@Column(name="Area")
	private String area;
	@Column(name="City")
	private String city;
	@Column(name="Pincode")
	private int pincode;
	@Column(name="State")
	private String state;
	@Column(name="Country")
	private String country;
	
	public Address() 
	{
		
	}
	public Address(int id, String registeredEmail, String name, int mobile, String houseNumber, String area,
			String city, int pincode, String state, String country) {
		super();
		this.id = id;
		this.registeredEmail = registeredEmail;
		this.name = name;
		this.mobile = mobile;
		this.houseNumber = houseNumber;
		this.area = area;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegisteredEmail() {
		return registeredEmail;
	}
	public void setRegisteredEmail(String registeredEmail) {
		this.registeredEmail = registeredEmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
