package com.hf.spring.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hf.spring.jpa.dao.EmployeeDao;
import com.hf.spring.jpa.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		if(employee.getId()==null){
			this.em.persist(employee);
		}else{
			this.em.merge(employee);
		}
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return this.em.find(Employee.class, id);
	}

	@Override
	public List<Employee> findEmployees() {
		// TODO Auto-generated method stub
		return this.em.createQuery("from Employee").getResultList();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.em.remove(this.em.find(Employee.class, id));;
	}

}
