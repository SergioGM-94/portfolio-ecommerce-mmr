package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByCategoryId(int idcategory);
	List<Product> findByOrderByTitleAsc();
    List<Product> findByOrderByTitleDesc();
}
