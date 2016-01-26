package com.hf.spring.jpa.service;

import java.util.List;

import com.hf.spring.jpa.entity.Employee;

public interface EmployeeService {
	void saveEmployee(Employee employee);
	void deleteEmployee(int id);
	Employee findEmplyeeById(int id);
	List<Employee> findEmployees();
}
