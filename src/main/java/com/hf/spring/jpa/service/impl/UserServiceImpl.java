package com.hf.spring.jpa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.jpa.annotation.Cacheable;
import com.hf.spring.jpa.annotation.Cacheable.KeyMode;
import com.hf.spring.jpa.dao.UserDao;
import com.hf.spring.jpa.entity.User;
import com.hf.spring.jpa.service.UserService;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public User findUserById(int id) {
		return userDao.findById(id);
	}
	
	@Cacheable(expire=3000,keyMode=KeyMode.ALL)
	//@Cacheable(value = "user", key = "#username")
	@Override
	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
