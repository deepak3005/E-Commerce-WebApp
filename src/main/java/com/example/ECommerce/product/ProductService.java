package com.example.ECommerce.product;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService 
{
	List<Product> getAllProducts(int solutionId);

	void saveProduct(Product product);

	void deleteProductById(int id);

	Product getProductById(int id);

	void saveProductToDB(MultipartFile file, String name, String type, String specs, double originalPrice,
			double netPrice, int solution_Id);

	void updateProductToDB(MultipartFile file, int id, String name, String type, String specs, double originalPrice,
			double netPrice, int solution_Id);

}