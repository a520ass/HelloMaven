package com.hf.spring.springdata.repository;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.hf.spring.springdata.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Customer getById(Integer id);
	
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true")}) 
	@Query("select c from Customer c where c.id=(select max(c2.id) from Customer c2))")
	Customer findMaxIdCustomer();
}
