package com.hf.spring.jpa.service;

import com.hf.spring.jpa.entity.User;

public interface UserService {
	User findUserById(int id);
	User findUserByUsername(String username);
}
