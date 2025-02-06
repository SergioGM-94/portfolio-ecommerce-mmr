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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.Product;
import com.portfolio.service.ProductService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-angular-ecommerce-mmr.vercel.app"})
public class ProductController {
	@Autowired
	public ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> listProducts(
			@RequestParam(required=false) Integer categoryId,
			@RequestParam(required = false, defaultValue = "asc") String sort){
		List<Product> products;
		
		if (categoryId != null && categoryId > 0) {

			products = service.listByCategoryId(categoryId);
            if ("desc".equalsIgnoreCase(sort)) {
				products.sort((p1, p2) -> p2.getTitle().compareToIgnoreCase(p1.getTitle()));
            } else {
				products.sort((p1, p2) -> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
            }
        } else {
           
            if ("desc".equalsIgnoreCase(sort)) {
				products = service.listProductsSortDsc();
            } else {
				products = service.listProductsSortAsc();
            }
        }
        
        return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		return ResponseEntity.ok(service.getProduct(id)) ;
	}
	
	@PostMapping
	public ResponseEntity<?> addProduct(@Validated @RequestBody Product p) {
		try {
			Product nuevo = service.saveProduct(p);
			return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al agregar el producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(
			@PathVariable int id,
			@RequestBody Product product){
		Product p = service.getProduct(id);
		p.setTitle(product.getTitle());
		p.setPrice(product.getPrice());
		p.setCategoryId(product.getCategoryId());
		p.setDescription(product.getDescription());
		p.setImage(product.getImage());
		
		service.saveProduct(p);
		
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
		Product deletedProduct = service.getProduct(id);
		service.deleteProduct(id);
		return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
	}

}
