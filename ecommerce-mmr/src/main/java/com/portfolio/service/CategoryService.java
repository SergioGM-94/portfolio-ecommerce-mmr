package com.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.model.Category;
import com.portfolio.repository.ICategoryRepository;

@Service
public class CategoryService {
	@Autowired
	public ICategoryRepository catRepo;
	
	public List<Category> listCategories(){
		return catRepo.findAll();
	}
	
	public Category saveCategory(Category c) {
		return catRepo.save(c);
	}
	
	public Category getCategory(int id) {
		return catRepo.findById(id).get();
	}
	
	public String deleteCategory(int id) {
		catRepo.deleteById(id);
		return "Categoria Eliminada";
	}
}
