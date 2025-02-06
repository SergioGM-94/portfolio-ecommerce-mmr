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

import com.portfolio.model.Category;
import com.portfolio.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-angular-ecommerce-mmr.vercel.app"})
public class CategoryController {
	@Autowired
	public CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> listCategories(){
		return ResponseEntity.ok(service.listCategories());
	}
	
	@PostMapping
	public ResponseEntity<?> addCategory(@Validated @RequestBody Category c) {
		try {
			Category nuevo = service.saveCategory(c);
			return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al agregar la categoría: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(
			@PathVariable int id,
			@RequestBody Category category){
		Category c = service.getCategory(id);
		c.setName(category.getName());

		service.saveCategory(c);
		
		return new ResponseEntity<Category>(c, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
		Category deletedCategory = service.getCategory(id);
		service.deleteCategory(id);
		return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
	}
}
