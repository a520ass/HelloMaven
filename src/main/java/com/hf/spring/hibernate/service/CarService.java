package com.hf.spring.hibernate.service;

import java.util.List;

import com.hf.spring.hibernate.entities.Car;

public interface CarService {
	
	public List<Car> findAll();
	
	public long findNum();
	public Car find(String id);
}
