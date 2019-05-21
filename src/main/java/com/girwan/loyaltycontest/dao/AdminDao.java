package com.girwan.loyaltycontest.dao;

import java.util.List;

import com.girwan.loyaltycontest.model.Admin;

public interface AdminDao {
	void addAdmin(Admin a);
	void removeAdmin(int id);
	void update(Admin a);
	List<Admin> getAll();
	Admin getById(int id);
	boolean login(String un,String pw);
	
}
