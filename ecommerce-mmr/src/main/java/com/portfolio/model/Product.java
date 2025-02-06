package com.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String title;
	public double price;
	public int categoryId;
	public String description;
	public String image;
	
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
	private Category categoryObj;

}
