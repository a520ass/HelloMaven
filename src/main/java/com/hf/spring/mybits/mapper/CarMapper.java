package com.hf.spring.mybits.mapper;

import java.util.List;

import com.hf.spring.mybits.MyBatisDao;
import com.hf.spring.mybits.bean.Car;

@MyBatisDao
public interface CarMapper {
	public List<Car> findAll();
}
