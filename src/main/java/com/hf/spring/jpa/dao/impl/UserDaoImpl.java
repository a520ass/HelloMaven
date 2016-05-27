package com.hf.spring.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hf.spring.jpa.dao.UserDao;
import com.hf.spring.jpa.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public User findById(int id) {
		return this.em.find(User.class, id);
	}
	
	
	@Override
	public User findByUsername(String username) {
		return (User) this.em.createQuery("from User where username=:username").setParameter("username", username).getSingleResult();
	}

}
