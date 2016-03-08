package com.hf.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hf.spring.springdata.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
