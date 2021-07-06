package com.example.ECommerce.cart;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ECommerce.login.model.User;
import com.example.ECommerce.product.Product;
import com.example.ECommerce.product.ProductRepository;

@Service
@Transactional
public class ShoppingCartServices 
{
	@Autowired
	private CartItemRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	public List<CartItem> listCartItems(User user)
	{
		return cartRepo.findByUser(user);
	}
	
	public Integer addProduct(int productId, User user)
	{
		int quantity = 1;
		
		Product product = productRepo.findById(productId).get();
		
		CartItem cartItem = cartRepo.findByUserAndProduct(user, product);
		
		if(cartItem==null)
		{
			cartItem = new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setUser(user);
			cartItem.setProduct(product);
			
			cartRepo.save(cartItem);
		}
		
		return quantity;
	}
	
	public double updateQuantity(int productId, int quantity, User user)
	{
		cartRepo.updateQuantity(quantity, productId, user.getId());
		Product product = productRepo.findById(productId).get();
		double subtotal = product.getNetPrice() * quantity;
		return subtotal;
	}
	
	public void removeProductFromCart(int productId, User user)
	{
		cartRepo.deleteByUserAndProduct(user.getId(), productId);
	}
}
