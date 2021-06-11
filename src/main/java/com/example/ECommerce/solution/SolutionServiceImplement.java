package com.example.ECommerce.solution;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionServiceImplement implements SolutionService
{
	@Autowired
	private SolutionRepository solutionRepository;
	
	@Override
	public List<Solution> getAllSolutions() 
	{
		return solutionRepository.findAll();
	}

	@Override
	public void saveSolution(Solution solution) 
	{
		this.solutionRepository.save(solution);
	}

	@Override
	public Solution getSolutionById(int id) 
	{
		Optional<Solution> optional = solutionRepository.findById(id);
		Solution solution = null;
		if(optional.isPresent())
		{
			solution = optional.get();
		}
		else
		{
			throw new RuntimeException("Solution not found for id :: "+id);
		}
		return solution;
	}

	@Override
	public void deleteSolutionById(int id) 
	{
		this.solutionRepository.deleteById(id);
	}
}
