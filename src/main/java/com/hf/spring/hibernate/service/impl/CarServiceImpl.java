package com.hf.spring.hibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.hibernate.dao.CarDao;
import com.hf.spring.hibernate.entities.Car;
import com.hf.spring.hibernate.service.CarService;

@Transactional(readOnly=true)
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;

	@Override
	public List<Car> findAll() {
		// TODO Auto-generated method stub
		return carDao.findAll();
	}

	@Override
	public long findNum() {
		// TODO Auto-generated method stub
		return carDao.findNum();
	}

	@Override
	public Car find(String id) {
		// TODO Auto-generated method stub
		return carDao.find(id);
	}
}
