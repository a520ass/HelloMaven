package com.hf.spring.mybits.bean;

public class Car {
	private int id;
	private String name;
	private char password;
	private float price;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getPassword() {
		return password;
	}

	public void setPassword(char password) {
		this.password = password;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Car(int id, String name, char password, float price) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.price = price;
	}

	public Car(int id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Car() {
		super();
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", password=" + password
				+ ", price=" + price + "]";
	}
}
