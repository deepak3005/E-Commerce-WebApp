package com.example.ECommerce.userAddress;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ECommerce.login.model.User;
import com.example.ECommerce.login.service.UserService;
import com.example.ECommerce.solution.Solution;

@Controller
public class AddressController 
{
	@Autowired
	private AddressServices addressService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/address")
	public String showAddressPage(Model model)
	{
		User user = userService.getCurrentlyLoggedInUser();
		
		String registeredEmail = user.getEmail();
		
		List<Address> address = addressService.getAllAddresses(registeredEmail);
		
		model.addAttribute("address", address);
		
		return "address";
	}
	
	@GetMapping("/addNewAddress")
	public String showNewAddressForm(Model model)
	{
		Address address = new Address();
		model.addAttribute("address", address);
		return "newAddress";
	}
	
	@PostMapping("/saveAddress")
	public String saveNewAddress(@ModelAttribute("address") Address address)
	{
		addressService.saveNewAddress(address);
		return "redirect:/address";
	}
	
	@GetMapping("/showUpdateAddressForm/{id}")
	public String showFormForUpdate(@PathVariable(value="id") int id, Model model)
	{
		Address address = addressService.getAddressById(id);
		model.addAttribute("address", address);
		return "updateAddress";
	}
	
	@GetMapping("/deleteAddress/{id}")
	public String deleteSolution(@PathVariable(value="id") int id)
	{
		this.addressService.deleteSolutionById(id);
		return "redirect:/address";
	}
}
