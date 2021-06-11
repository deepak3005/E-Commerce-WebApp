package com.example.ECommerce.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ECommerce.solution.Solution;

@Entity
@Table(name="Product")
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Name")
	private String name;
	@Column(name="Type")
	private String type;
	@Column(name="Specifications")
	private String specs;
	@Column(name="OriginalPrice")
	private double originalPrice;
	@Column(name="NetPrice")
	private double netPrice;
	
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private String image;
	
	@ManyToOne
	private Solution solution;

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public Product() 
	{
		
	}

	public Product(int id, String name, String type, String specs, double originalPrice, double netPrice, String image, int solutionId)
	{
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.specs = specs;
		this.originalPrice = originalPrice;
		this.netPrice = netPrice;
		this.image = image;
		this.solution = new Solution(solutionId,"","");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
