package com.hf.spring.springdata.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="SJPA_ORDERS")
@Entity
public class Order {
	
	private Integer id;
	private String orderName;
	private Customer customer;
	private Customer customerTransient;
	
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
	
	@JsonIgnore
	@JoinColumn(name="CUSTOMER_ID")
	@ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customerTransient=customer;
		this.customer = customer;
	}
	public Order() {
		super();
	}
	@Transient
	public Customer getCustomerTransient() {
		return customerTransient;
	}
	public void setCustomerTransient(Customer customerTransient) {
		this.customerTransient = customerTransient;
		this.customer=customerTransient;
	}
	

}
