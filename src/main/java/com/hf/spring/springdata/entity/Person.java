package com.hf.spring.springdata.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="SJPA_PERSONS")
@Entity(name="com.hf.spring.springdata.entity.Person")
public class Person {
	
	private Integer id;
	private String lastName;
	private String email;
	private Date birth;
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Person() {
		super();
	}
	public Person(Integer id, String lastName, String email, Date birth) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", birth=" + birth + "]";
	}
}
