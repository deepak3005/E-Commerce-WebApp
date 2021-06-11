package com.example.ECommerce.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ECommerce.solution.Solution;

import org.springframework.util.StringUtils;
import java.util.Base64;

@Service
public class ProductServiceImplement implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts(int solutionId) 
	{
		List<Product> Products = new ArrayList<>();
		productRepository.findBySolutionId(solutionId).forEach(Products::add);
		return Products;
	}
	
	@Override
	public Product getProductById(int id) 
	{
		Optional<Product> optional = productRepository.findById(id);
		Product product = null;
		if(optional.isPresent())
		{
			product = optional.get();
		}
		else
		{
			throw new RuntimeException("Product not found for id :: "+id);
		}
		return product;
	}

	@Override
	public void saveProduct(Product product) 
	{
		productRepository.save(product);
	}

	@Override
	public void deleteProductById(int id) 
	{
		this.productRepository.deleteById(id);
	}

	@Override
	public void saveProductToDB(MultipartFile file, String name, String type, String specs, double originalPrice,
			double netPrice, int solution_Id) 
	{
		Product product = new Product();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try 
		{
			product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		product.setName(name);
		product.setType(type);
		product.setSpecs(specs);
		product.setOriginalPrice(originalPrice);
		product.setNetPrice(netPrice);
		product.setSolution(new Solution(solution_Id,"",""));
        
        productRepository.save(product);
	}

	@Override
	public void updateProductToDB(MultipartFile file, int id, String name, String type, String specs,
			double originalPrice, double netPrice, int solution_Id) 
	{
		Product product = new Product();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try 
		{
			product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		product.setId(id);
		product.setName(name);
		product.setType(type);
		product.setSpecs(specs);
		product.setOriginalPrice(originalPrice);
		product.setNetPrice(netPrice);
		product.setSolution(new Solution(solution_Id,"",""));
        
        productRepository.save(product);
	}
	
}
