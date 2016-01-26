package com.hf.spring.jpa.dao;

import java.util.List;

import com.hf.spring.jpa.entity.Employee;

public interface EmployeeDao {
	void save(Employee employee);
	void delete(int id);
	Employee findById(int id);
	List<Employee> findEmployees();
}
