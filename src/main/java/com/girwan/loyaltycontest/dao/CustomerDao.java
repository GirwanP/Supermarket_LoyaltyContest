package com.girwan.loyaltycontest.dao;

import java.util.List;

import com.girwan.loyaltycontest.model.Customer;

public interface CustomerDao {
	void signup(Customer c);
	boolean login(String un,String pw);
	List<Customer> getAll();
	Customer getById(int id);
	void removeCustomer(int id);
	void updateCustomer(Customer c);
	void updateScore(int id,int score);
	int getScore(int id);
	
	boolean isUserNameValid(String un);
}
