package com.example.demo;

import java.util.UUID;

public class Address {
	private long id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	
	//constructor
	public Address(long id, String fname, String lname, String address, String email){
		this.id = id;
		this.firstName = fname;
		this.lastName = lname;
		this.address = address;
		this.email = email;
	}
	public Address() {
		
	}
	
	public Address(String firstName, String lastName, String address, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", email=" + email + "]";
	}
	
	
}
