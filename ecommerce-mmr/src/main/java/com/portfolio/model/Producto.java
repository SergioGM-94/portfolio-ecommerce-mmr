package com.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String title;
	public int price;
	public int idcategory;
	public String description;
	public String image;
	
	@ManyToOne
	@JoinColumn(name = "idcategory", referencedColumnName = "idcategory", insertable = false, updatable = false)
	private Categoria objCategoria;

}
