package com.example.ECommerce.cart;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ECommerce.login.model.User;
import com.example.ECommerce.login.service.UserService;

@Controller
public class ShoppingCartController 
{
	@Autowired
	private ShoppingCartServices cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/userCart")
	public String showShoppingCart(Model model)
	{
		User user = userService.getCurrentlyLoggedInUser();
		List<CartItem> cartItems = cartService.listCartItems(user);
		
		model.addAttribute("cartItems", cartItems);
		
		return "userCart";
	}
}
