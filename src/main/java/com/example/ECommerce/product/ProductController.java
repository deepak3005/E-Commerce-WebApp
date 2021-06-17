package com.example.ECommerce.product;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ECommerce.solution.Solution;

@Controller
public class ProductController 
{
	private int solution_Id = 0;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/adminDashboard/showSolution/{id}")
	public String getAllProducts(@PathVariable("id") int solutionId, Model model)
	{
		solution_Id = solutionId;
		model.addAttribute("listProducts", productService.getAllProducts(solutionId));
		return "productsDashboard";
	}
	
	@RequestMapping("/userDashboard/showSolution/{id}")
	public String getAllProductsToUser(@PathVariable("id") int solutionId, Model model)
	{
		solution_Id = solutionId;
		model.addAttribute("listProducts", productService.getAllProducts(solutionId));
		return "userProductsDashboard";
	}
	
	/*@RequestMapping("/adminDashboard/showSolution/{solutionId}/showProduct{id}")
	public String getProduct(int id, Model model)
	{
		model.addAttribute("listProducts", productService.getProduct(id));
		return "productsDashboard";
	}*/
	
	@GetMapping("/showNewProductForm")
	public String showNewProductForm(Model model)
	{
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	/*@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product, Model model, HttpServletRequest request, final @RequestParam("image") MultipartFile file) throws IOException
	{
		product.setSolution(new Solution(solution_Id,"",""));
		
		productService.saveProduct(product);
		
		model.addAttribute("listProducts", productService.getAllProducts(solution_Id));
		return "productsDashboard";
	}*/
	
	@PostMapping("/saveProduct")
    public String saveProduct(@RequestParam("name") String name,
    		@RequestParam("type") String type,
    		@RequestParam("specs") String specs,
    		@RequestParam("originalPrice") double originalPrice,
    		@RequestParam("netPrice") double netPrice,
    		@RequestParam("file") MultipartFile file,
    		Model model)
    {
    	productService.saveProductToDB(file, name, type, specs, originalPrice, netPrice, solution_Id);
    	
    	model.addAttribute("listProducts", productService.getAllProducts(solution_Id));
		return "productsDashboard";
    }
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") int id, Model model)
	{
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "update_product";
	}
	
	@PostMapping("/updateProduct")
    public String updateProduct(@RequestParam("id") int id,
    		@RequestParam("name") String name,
    		@RequestParam("type") String type,
    		@RequestParam("specs") String specs,
    		@RequestParam("originalPrice") double originalPrice,
    		@RequestParam("netPrice") double netPrice,
    		@RequestParam("file") MultipartFile file,
    		Model model)
    {
    	productService.updateProductToDB(file, id, name, type, specs, originalPrice, netPrice, solution_Id);
    	
    	model.addAttribute("listProducts", productService.getAllProducts(solution_Id));
		return "productsDashboard";
    }
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value="id") int id, Model model)
	{
		this.productService.deleteProductById(id);
		//return "redirect:/adminDashboard";
		model.addAttribute("listProducts", productService.getAllProducts(solution_Id));
		return "productsDashboard";
	}
}
