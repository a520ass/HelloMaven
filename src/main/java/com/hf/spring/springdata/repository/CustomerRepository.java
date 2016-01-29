package com.hf.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hf.spring.springdata.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Customer getById(Integer id);
}
