package com.hf.spring.jpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.jpa.annotation.Cacheable;
import com.hf.spring.jpa.annotation.Cacheable.KeyMode;
import com.hf.spring.jpa.dao.DepartmentDao;
import com.hf.spring.jpa.entity.Department;
import com.hf.spring.jpa.service.DepartmentService;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentDao departmentDao;
	
	@Transactional(readOnly=false)
	@Override
	public void saveDepartment(Department department) {
		departmentDao.save(department);
	}
	
	@Override
	public Department findDepartmentById(int id) {
		return departmentDao.findById(id);
	}
	
	@Override
	public List<Department> findDepartments() {
		// TODO Auto-generated method stub
		return departmentDao.findDepartments();
	}

}
