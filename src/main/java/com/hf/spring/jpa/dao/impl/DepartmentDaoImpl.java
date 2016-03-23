package com.hf.spring.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hf.spring.jpa.dao.DepartmentDao;
import com.hf.spring.jpa.entity.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void save(Department department) {
		if(department.getId()==null){
			this.em.persist(department);
		}else{
			this.em.merge(department);
		}
	}

	@Override
	public Department findById(int id) {
		return this.em.find(Department.class, id);
	}

	@Override
	public List<Department> findDepartments() {
		return this.em.createQuery("from Department").getResultList();
	}

}
