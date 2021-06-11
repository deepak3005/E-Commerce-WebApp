package com.example.ECommerce.solution;

import java.util.List;

public interface SolutionService 
{
	List<Solution> getAllSolutions();
	
	void saveSolution(Solution solution);
	
	Solution getSolutionById(int id);
	
	void deleteSolutionById(int id);
}
