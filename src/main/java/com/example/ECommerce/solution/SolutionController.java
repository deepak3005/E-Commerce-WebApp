package com.example.ECommerce.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ECommerce.product.ProductService;

@Controller
public class SolutionController 
{
	private int solution_Id = 0;
	
	@Autowired
	private SolutionService solutionService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/adminDashboard")
	public String viewSolutions(Model model)
	{
		model.addAttribute("listSolutions", solutionService.getAllSolutions());
		return "adminDashboard";
	}
	
	@GetMapping("/userDashboard")
	public String viewSolutionsToUser(Model model)
	{
		model.addAttribute("listSolutions", solutionService.getAllSolutions());
		model.addAttribute("listProducts", productService.getAllProducts());
		return "userDashboard";
	}
	
	@RequestMapping("/userDashboard/showSolution/{id}")
	public String getAllProductsToUser(@PathVariable("id") int solutionId, Model model)
	{
		solution_Id = solutionId;
		model.addAttribute("listSolutions", solutionService.getAllSolutions());
		model.addAttribute("listProducts", productService.getAllProducts(solutionId));
		return "userDashboard";
	}
	
	@RequestMapping("/userDashboard/viewSingleProduct/{id}")
	public String getSingleProduct(@PathVariable("id") int productId, Model model)
	{
		model.addAttribute("listProducts", productService.getProductById(productId));
		return "viewSingleProduct";
	}
	
	@GetMapping("/adminDashboard/showNewSolutionForm")
	public String showNewSolutionForm(Model model)
	{
		Solution solution = new Solution();
		model.addAttribute("solution", solution);
		return "new_solution";
	}
	
	@PostMapping("/adminDashboard/saveSolution")
	public String saveSolution(@ModelAttribute("solution") Solution solution)
	{
		solutionService.saveSolution(solution);
		return "redirect:/adminDashboard";
	}
	
	@GetMapping("/adminDashboard/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") int id, Model model)
	{
		Solution solution = solutionService.getSolutionById(id);
		model.addAttribute("solution", solution);
		return "update_solution";
	}
	
	@GetMapping("/adminDashboard/deleteSolution/{id}")
	public String deleteSolution(@PathVariable(value="id") int id)
	{
		this.solutionService.deleteSolutionById(id);
		return "redirect:/adminDashboard";
	}

}
