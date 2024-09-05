package com.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.Categoria;
import com.portfolio.service.CategoriaService;

@RestController
@RequestMapping("/api/Categoria")
@CrossOrigin("http://localhost:4200")
public class CategoriaController {
	@Autowired
	public CategoriaService servicio;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias(){
		return ResponseEntity.ok(servicio.listarCategorias());
	}
}
