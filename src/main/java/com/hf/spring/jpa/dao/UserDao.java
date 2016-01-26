package com.hf.spring.jpa.dao;

import com.hf.spring.jpa.entity.User;

public interface UserDao {
	User findById(int id);
	User findByUsername(String username);
}
