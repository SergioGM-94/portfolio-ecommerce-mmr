package com.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portfolio.model.Product;
import com.portfolio.repository.IProductRepository;

@Service
public class ProductService {
	@Autowired
	public IProductRepository repoProd;
	
	public List<Product> listProducts(){
		return repoProd.findAll();
	}
	
	public List<Product> listByCategoryId(int idcategory){
		return repoProd.findByCategoryId(idcategory);
	}
	
	public List<Product> listProductsSortAsc() {
        return repoProd.findByOrderByTitleAsc();
    }

    public List<Product> listProductsSortDsc() {
        return repoProd.findByOrderByTitleDesc();
    }
	
	public Product saveProduct(Product p) {
		return repoProd.save(p);
	}
	
	public Product getProduct(int id) {
		return repoProd.findById(id).get();
	}
	
	public String deleteProduct(int id) {
		repoProd.deleteById(id);
		return "Producto Eliminado";
	}
	
	public Page<Product> listProducts(Pageable page){
		return repoProd.findAll(page);
	}

}
