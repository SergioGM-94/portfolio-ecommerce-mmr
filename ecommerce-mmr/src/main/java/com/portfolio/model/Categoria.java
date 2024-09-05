package com.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	private int idcategory;
	
	private String nombre;
}
