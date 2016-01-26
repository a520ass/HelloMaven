package com.hf.spring.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.jpa.annotation.Cacheable;
import com.hf.spring.jpa.annotation.Cacheable.KeyMode;
import com.hf.spring.jpa.dao.EmployeeDao;
import com.hf.spring.jpa.entity.Employee;
import com.hf.spring.jpa.service.EmployeeService;

@Service
@Transactional(readOnly=true)
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;
	@Transactional(readOnly=false)
	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.save(employee);
	}

	@Override
	public Employee findEmplyeeById(int id) {
		// TODO Auto-generated method stub
		return employeeDao.findById(id);
	}
	
	@Override
	public List<Employee> findEmployees() {
		// TODO Auto-generated method stub
		return employeeDao.findEmployees();
	}
	
	@Transactional(readOnly=false)
	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		employeeDao.delete(id);
	}

}
