package com.example.ECommerce.cart;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ECommerce.login.model.User;
import com.example.ECommerce.login.service.UserService;

@Controller
public class ShoppingCartRestController 
{
	@Autowired
	private ShoppingCartServices cartService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/cart/add/{pid}")
	public String addProductToCart(@PathVariable("pid") int productId)
	{
		User user = userService.getCurrentlyLoggedInUser();
		
		Integer quantity = cartService.addProduct(productId, user);
		
		return "redirect:/userCart";
	}
	
	@RequestMapping("/cart/update/{pid}/{qty}")
	public String updateQuantity(@PathVariable("pid") int productId, @PathVariable("qty") int quantity)
	{
		User user = userService.getCurrentlyLoggedInUser();
		
		double subtotal = cartService.updateQuantity(productId, quantity, user);
		
		return String.valueOf(subtotal);
	}
	
	@RequestMapping("/cart/remove/{pid}")
	public String removeProductFromCart(@PathVariable("pid") int productId)
	{
		User user = userService.getCurrentlyLoggedInUser();
		
		cartService.removeProductFromCart(productId, user);
		
		return "redirect:/userCart";
	}
}
