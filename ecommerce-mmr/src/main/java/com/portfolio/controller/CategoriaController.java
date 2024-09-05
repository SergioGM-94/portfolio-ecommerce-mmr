package com.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.Categoria;
import com.portfolio.model.Producto;
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
	
	@PostMapping
	public ResponseEntity<?> agregarCategoria(@Validated @RequestBody Categoria c) {
		try {
			Categoria nuevo = servicio.grabarCategoria(c);
			return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al agregar la categoría: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> actualizarCategoria(
			@PathVariable int id,
			@RequestBody Categoria categoria){
		Categoria c = servicio.obtenerCategoria(id);
		c.setNombre(categoria.getNombre());
			
		servicio.grabarCategoria(c);
		
		return new ResponseEntity<Categoria>(c, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> eliminarCategoria(@PathVariable int id) {
		Categoria categoriaEliminada = servicio.obtenerCategoria(id);
		servicio.borrarCategoria(id);
		return new ResponseEntity<>(categoriaEliminada, HttpStatus.OK);
	}
}
