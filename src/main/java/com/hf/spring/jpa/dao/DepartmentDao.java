package com.hf.spring.jpa.dao;

import java.util.List;

import com.hf.spring.jpa.entity.Department;

public interface DepartmentDao {
	void save(Department department);
	Department findById(int id);
	List<Department> findDepartments();
} 
