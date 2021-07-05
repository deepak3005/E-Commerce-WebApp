package com.example.ECommerce.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ECommerce.login.model.User;
import com.example.ECommerce.product.Product;
import com.example.ECommerce.product.ProductRepository;
import com.example.ECommerce.userAddress.Address;
import com.example.ECommerce.userAddress.AddressRepository;

@Service
public class OrderService 
{
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private ProductRepository productRepo;

	public List<Order> listOrders(User user) 
	{
		return orderRepo.findByUser(user);
	}

	public void addOrder(int product_id, String orderId, User user, String dateTime, int selectedAddressId, int quantity) 
	{
		Product product = productRepo.findById(product_id).get();
		
		Address address = addressRepo.findById(selectedAddressId).get();
		
		Order order = new Order();
		
		order.setOrderId(orderId);
		order.setDateandtime(dateTime);
		order.setProduct(product);
		order.setUser(user);
		order.setAddress(address);
		order.setQuantity(quantity);
		
		orderRepo.save(order);
	}
	
}
