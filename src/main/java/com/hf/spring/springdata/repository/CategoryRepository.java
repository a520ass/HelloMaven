package com.hf.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hf.spring.springdata.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
}
