package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer>{

}
