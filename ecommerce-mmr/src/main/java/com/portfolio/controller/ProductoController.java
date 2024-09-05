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

import com.portfolio.model.Producto;
import com.portfolio.service.ProductoService;

@RestController
@RequestMapping("/api/Producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
	@Autowired
	public ProductoService servicio;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listarProductos(
			@RequestParam(required=false) Integer idcategory,
			@RequestParam(required = false, defaultValue = "asc") String sort){
		List<Producto> productos;
		
		if (idcategory != null && idcategory > 0) {
            
            productos = servicio.listarPorIdCategoria(idcategory);
            if ("desc".equalsIgnoreCase(sort)) {
                productos.sort((p1, p2) -> p2.getTitle().compareToIgnoreCase(p1.getTitle()));
            } else {
                productos.sort((p1, p2) -> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
            }
        } else {
           
            if ("desc".equalsIgnoreCase(sort)) {
                productos = servicio.listarProductosOrdenadosDesc();
            } else {
                productos = servicio.listarProductosOrdenadosAsc();
            }
        }
        
        return ResponseEntity.ok(productos);		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerProductos(@PathVariable int id) {
		return ResponseEntity.ok(servicio.obtenerProducto(id)) ;
	}
	
	@PostMapping
	public ResponseEntity<?> agregarAtencion(@Validated @RequestBody Producto p) {
		try {
			Producto nuevo = servicio.grabarProducto(p);
			return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al agregar la atención: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> actualizarProducto(
			@PathVariable int id,
			@RequestBody Producto producto){
		Producto p = servicio.obtenerProducto(id);
		p.setTitle(producto.getTitle());
		p.setPrice(producto.getPrice());
		p.setIdcategory(producto.getIdcategory());	
		p.setDescription(producto.getDescription());	
		p.setImage(producto.getImage());
		
		servicio.grabarProducto(p);
		
		return new ResponseEntity<Producto>(p, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Producto> eliminarProducto(@PathVariable int id) {
		Producto productoEliminado = servicio.obtenerProducto(id);
		servicio.borrarProducto(id);
		return new ResponseEntity<>(productoEliminado, HttpStatus.OK); //servicio.borrarAtencion(id);
	}

}
