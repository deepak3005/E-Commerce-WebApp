package com.example.ECommerce.userAddress;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ECommerce.login.model.User;
import com.example.ECommerce.login.service.UserService;
import com.example.ECommerce.solution.Solution;

@Service
@Transactional
public class AddressServices 
{
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Address> getAllAddresses(String registeredEmail)
	{
		System.out.println("\n\nGot registered email to services : "+registeredEmail+"\n\n");
		
		return addressRepository.getAllAddresses(registeredEmail);
	}
	
	public Address getAddressById(int id) 
	{
		Optional<Address> optional = addressRepository.findById(id);
		Address address = null;
		if(optional.isPresent())
		{
			address = optional.get();
		}
		else
		{
			throw new RuntimeException("Address not found for id :: "+id);
		}
		return address;
	}
	
	void saveNewAddress(Address address)
	{
		User user = userService.getCurrentlyLoggedInUser();
		
		String registeredEmail = user.getEmail();
		
		address.setRegisteredEmail(registeredEmail);
		
		this.addressRepository.save(address);
	}

	public void deleteSolutionById(int id) 
	{
		this.addressRepository.deleteById(id);
	}

}
