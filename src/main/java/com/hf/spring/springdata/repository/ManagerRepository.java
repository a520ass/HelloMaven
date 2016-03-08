package com.hf.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hf.spring.springdata.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{

}
