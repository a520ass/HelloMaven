package com.hf.spring.hibernate.entities;

import java.util.List;

public class Person {
	private String name;
	private char sex;
	private List<Car> cars;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public Person() {
		super();
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", cars=" + cars + "]";
	}
	
}
