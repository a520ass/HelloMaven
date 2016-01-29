package com.hf.spring.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="SJPA_ORDERS")
@Entity
public class Order {
	
	private Integer id;
	private String orderName;
	private Customer customer;
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="ORDER_NAME")
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	@JoinColumn(name="CUSTOMER_ID")
	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Order() {
		super();
	}
	public Order(Integer id, String orderName, Customer customer) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderName=" + orderName + ", customer="
				+ customer + "]";
	}
	
	

}
