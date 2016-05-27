package com.hf.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hf.spring.springdata.entity.Category;
import com.hf.spring.springdata.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
}
