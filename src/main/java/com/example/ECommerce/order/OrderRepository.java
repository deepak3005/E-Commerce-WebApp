package com.example.ECommerce.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ECommerce.login.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> 
{

	List<Order> findByUser(User user);
	
}
