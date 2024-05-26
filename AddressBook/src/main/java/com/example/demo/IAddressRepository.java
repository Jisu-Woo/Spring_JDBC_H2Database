package com.example.demo;

import java.util.List;

public interface IAddressRepository {
	
	Address add(Address address);
	
	//int update(Address address);
	
	List<Address> search(String lastname);
	
	int delete(String email);
	
	int deleteAll();
	
	Address findOne(String email);
	
	List<Address> findAll();
	
}
