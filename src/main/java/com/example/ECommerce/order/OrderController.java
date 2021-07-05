package com.example.ECommerce.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ECommerce.cart.CartItem;
import com.example.ECommerce.cart.ShoppingCartServices;
import com.example.ECommerce.login.model.User;
import com.example.ECommerce.login.service.UserService;
import com.example.ECommerce.userAddress.AddressController;
import com.example.ECommerce.userAddress.AddressServices;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

@Controller
public class OrderController 
{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressController addressController;
	
	@Autowired
	private ShoppingCartServices cartService;
	
	@Autowired
	private AddressServices addressService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/myOrders")
	public String showMyOrders(Model model)
	{
		User user = userService.getCurrentlyLoggedInUser();
		List<Order> orders = orderService.listOrders(user);
		
		model.addAttribute("orders", orders);
		
		return "myOrders";
	}
	
	@GetMapping("/placeOrder")
	public String placeOrder(Model model)
	{
		String orderId = "#" + getAlphaNumericString();    // auto-generated order id
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");  
		LocalDateTime now = LocalDateTime.now(); 
		String dateTime = dtf.format(now);
		
		int selectedAddressId = addressController.getAddressSelected();
		
		User user = userService.getCurrentlyLoggedInUser();
		List<CartItem> cartItems = cartService.listCartItems(user);
		
		for(CartItem cartitem : cartItems)
		{
			orderService.addOrder(cartitem.getProduct().getId(), orderId, user, dateTime, selectedAddressId, cartitem.getQuantity());
		}
		
		return "OrderPlaced";
	}
	
	static String getAlphaNumericString()
    {
		int n = 9;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) 
        {
            int index
                = (int)(AlphaNumericString.length() * Math.random());
  
            sb.append(AlphaNumericString
                          .charAt(index));
        }
        return sb.toString();
    }
	
	@RequestMapping("/orderDeliveredTo/{id}")
	public String orderDeliveredTo(@PathVariable("id") int addressId, Model model)
	{
		model.addAttribute("listAddress", addressService.getAddressById(addressId));
		return "orderDeliveredTo";
	}
	
}
