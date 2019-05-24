package com.girwan.loyaltycontest.dao;

import java.util.List;

import com.girwan.loyaltycontest.model.Customer;
import com.girwan.loyaltycontest.model.Score;

public interface CustomerDao {
	void signup(Customer c);
	boolean login(String un,String pw);
	List<Customer> getAll();
	Customer getById(int id);
	Customer getByEmail(String email);
	void removeCustomer(int id);
	void updateCustomer(Customer c);
	void updateScore(String email,Score score);
	int getScore(int id);
	
	boolean isUserNameValid(String un);
}
