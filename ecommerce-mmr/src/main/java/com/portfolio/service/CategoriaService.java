package com.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.model.Categoria;
import com.portfolio.model.Producto;
import com.portfolio.repository.ICategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	public ICategoriaRepository repoCat;
	
	public List<Categoria> listarCategorias(){
		return repoCat.findAll();
	}
	
	public Categoria grabarCategoria(Categoria c) {
		return repoCat.save(c);
	}
	
	public Categoria obtenerCategoria(int id) {
		return repoCat.findById(id).get();
	}
	
	public String borrarCategoria(int id) {
		repoCat.deleteById(id);
		return "Categoria Eliminada";
	}
}
