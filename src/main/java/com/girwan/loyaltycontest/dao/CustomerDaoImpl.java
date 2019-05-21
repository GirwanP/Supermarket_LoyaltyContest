package com.girwan.loyaltycontest.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.girwan.loyaltycontest.model.Customer;

@Repository
public class CustomerDaoImpl implements  CustomerDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	@Transactional
	public void signup(Customer c) {
		Session sess= sessionFactory.getCurrentSession();
		sess.save(c);
		
	}

	@Override
	public boolean login(String un, String pw) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCustomer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateScore(int id, int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getScore(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUserNameValid(String un) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
