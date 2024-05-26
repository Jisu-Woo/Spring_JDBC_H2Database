package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressBookManager implements IAddressBookManager {
	
	//public ArrayList<Address> list = new ArrayList<Address>();
	@Autowired
	private IAddressRepository repo; 

	@Override
	public boolean addAddress(String fname, String lname, String address, String email) {
		//email unique check
		List<Address> l = repo.findAll();
		
		for(int i = 0; i < l.size(); i++) {
			if(l.get(i).getEmail().equals(email)) {
				return false;
			}
			
		}

		/*UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		boolean isEqual = false;
		do {
			isEqual = false;
			UUID uuid = UUID.randomUUID();
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getId().equals(uuid)) {
					isEqual = true;
				}
			}
			if(!(isEqual)) {
				uid = uuid;
				break;
			}
		} while(isEqual);
				*/
		
		Address a = new Address(fname, lname, address, email);		
		repo.add(a);
		return true;

	}

	@Override
	public Address emailToAddress(String email) { 
		return repo.findOne(email);

		// 없으면 null 
	}

	@Override
	public List<Address> lastToAddress(String lastName) {
		return repo.search(lastName);
		
		/*for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getLastName().equals(lastName)) {
				
				a.add(list.get(i));
			}
		}*/
		//일치하는 lastName을 가진 Address list를 만들어서 return
		
		
	}

	@Override
	public List<Address> allAddress() {
		
		return repo.findAll();
	}

	@Override
	public boolean remove(String email) {
		
		if(repo.delete(email) != 0) {
			return true;
		}
		else {
			return false;
		}
		/*
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getEmail().equals(email)) {
				list.remove(i);

				return true;
			}
		}
		//동일 email 존재X
		return false;
		*/
	}

	@Override
	public boolean removeAll() {
		if(repo.deleteAll() != 0) {
			return true;
		}
		else {
			return false;
		}
		/*
		list.clear();
		// TODO Auto-generated method stub
		return true;*/
	}


	
	
}
