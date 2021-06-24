package com.example.ECommerce.userAddress;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ECommerce.solution.Solution;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>
{
	@Query(value="SELECT * FROM Address c WHERE c.registered_email = ?1", nativeQuery=true)
	public List<Address> getAllAddresses(String registeredEmail);
	
}
