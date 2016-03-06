package com.hf.spring.springdata.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.springdata.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Transactional(readOnly=false)
	public int updatePersonEmail(Integer id,String email){
		return personRepository.updatePersonEmail(id, email);
	}
}
