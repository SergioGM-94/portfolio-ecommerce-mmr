package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByIdcategory(int idcategory);
	List<Producto> findByOrderByTitleAsc();
    List<Producto> findByOrderByTitleDesc();
}
