package com.hf.spring.hibernate.dao;

import java.util.List;

import com.hf.spring.hibernate.entities.Car;

public interface CarDao {
	public List<Car> findAll();
	public long findNum();
	public Car find(String id);
}
