package com.hf.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hf.spring.springdata.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
