package com.hf.spring.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.hf.spring.jpa.dao.UserDao;
import com.hf.spring.jpa.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return this.em.find(User.class, id);
	}
	
	//@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") }) 
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return (User) this.em.createQuery("from User where username=:username").setParameter("username", username).getSingleResult();
	}

}
