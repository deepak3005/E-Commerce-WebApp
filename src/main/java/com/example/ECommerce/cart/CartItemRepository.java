package com.example.ECommerce.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ECommerce.login.model.User;
import com.example.ECommerce.product.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>
{
	public List<CartItem> findByUser(User user);
	
	public CartItem findByUserAndProduct(User user, Product product);
	
	@Query(value="UPDATE cart_items c SET c.quantity = ?1 WHERE c.product_id = ?2 AND c.user_id = ?3", nativeQuery=true)
	@Modifying
	public void updateQuantity(int quantity, int productId, Long userId);
	
	@Query(value="DELETE FROM cart_items c WHERE c.user_id = ?1 AND c.product_id = ?2", nativeQuery=true)
	@Modifying
	public void deleteByUserAndProduct(Long userId, int productId);
}
