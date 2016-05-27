package com.hf.spring.springdata.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable(true)
@Table(name="SJPA_CUTOMERS")
@Entity
public class Customer {
	
	private Integer id;
	private String lastName;
	private String email;
	private int age;
	private Date createdTime;
	private Date birth;
	
	private Set<Order> orders =new HashSet<>();
	private Set<Order> ordersTransient =new HashSet<>();
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	//单向1-n的关联关系
	//@JoinColumn(name="CUSTOMER_ID")
	@JsonIgnore
	@OneToMany(cascade=CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy="customer")
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.ordersTransient=orders;
		this.orders = orders;
	}
	public Customer() {
		super();
	}
	@Transient
	public Set<Order> getOrdersTransient() {
		return ordersTransient;
	}
	public void setOrdersTransient(Set<Order> ordersTransient) {
		this.ordersTransient = ordersTransient;
		this.orders=ordersTransient;
	}
	
	
}
