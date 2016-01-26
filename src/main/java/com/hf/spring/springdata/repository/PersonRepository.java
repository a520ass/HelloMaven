package com.hf.spring.springdata.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.hf.spring.springdata.entity.Person;

public interface PersonRepository extends Repository<Person, Integer>{
	
	Person getById(Integer id);
	List<Person> getByEmailNotNull();
	List<Person> getByIdBetween(Integer idless,Integer idmore);
}
