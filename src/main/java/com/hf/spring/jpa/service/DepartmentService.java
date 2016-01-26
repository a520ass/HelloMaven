package com.hf.spring.jpa.service;

import java.util.List;

import com.hf.spring.jpa.entity.Department;

public interface DepartmentService {
	void saveDepartment(Department department);
	Department findDepartmentById(int id);
	List<Department> findDepartments();
}
