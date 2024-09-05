package com.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portfolio.model.Producto;
import com.portfolio.repository.IProductoRepository;

@Service
public class ProductoService {
	@Autowired
	public IProductoRepository repoProd;
	
	public List<Producto> listarProductos(){
		return repoProd.findAll();
	}
	
	public List<Producto> listarPorIdCategoria(int idcategory){
		return repoProd.findByIdcategory(idcategory);
	}
	
	public List<Producto> listarProductosOrdenadosAsc() {
        return repoProd.findByOrderByTitleAsc();
    }

    public List<Producto> listarProductosOrdenadosDesc() {
        return repoProd.findByOrderByTitleDesc();
    }
	
	public Producto grabarProducto(Producto p) {
		return repoProd.save(p);
	}
	
	public Producto obtenerProducto(int id) {
		return repoProd.findById(id).get();
	}
	
	public String borrarProducto(int id) {
		repoProd.deleteById(id);
		return "Producto Eliminado";
	}
	
	public Page<Producto> listarProductos(Pageable pagina){
		return repoProd.findAll(pagina);
	}

}
