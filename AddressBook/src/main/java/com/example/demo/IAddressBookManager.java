package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public interface IAddressBookManager {

	public boolean addAddress(String fname, String lname, String address, String email);
	public Address emailToAddress(String email);
	public List<Address> lastToAddress(String lastName);
	public List<Address> allAddress();
	public boolean remove(String email);
	public boolean removeAll(); 

}
