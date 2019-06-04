package com.girwan.loyaltycontest.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.girwan.loyaltycontest.model.Customer;
import com.girwan.loyaltycontest.model.Score;
@Repository
public class TestDaoImpl implements TestDao{
	@Resource
	private SessionFactory sessionFactory;
	
	
	
	@Transactional
	public void signup(Customer c) {
		Session sess= sessionFactory.getCurrentSession();
		sess.save(c);
		
	}

	
	@Transactional
	public boolean login(String em, String pw) {
		Session sess=sessionFactory.getCurrentSession();
		Criteria crt=sess.createCriteria(Customer.class);
		crt.add(Restrictions.eq("email",em)).add(Restrictions.eq("password", pw));
		
		Customer c=(Customer)crt.uniqueResult();
		System.out.println("Inside login");
		if(c!=null){
			return true;
		}
		return false;
	}

	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void removeCustomer(int id) {
		// TODO Auto-generated method stub
		
	}

	
	public void updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		
	}

	
	@Transactional
	public void updateScore(String email, Score score) {
		Session sess=sessionFactory.getCurrentSession();
		Criteria crt=sess.createCriteria(Customer.class);
		crt.add(Restrictions.eq("email", email));
		Customer c=(Customer)crt.uniqueResult();
		//List<Score> sl=c.;;
		//sl.add(score);
		c.updateScores(score);
		sess.save(score);
		sess.update(c);
		
	}

	
	public int getScore(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean isUserNameValid(String un) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Transactional
	public Customer getByEmail(String email) {
		System.out.println("inside Testaoimpl getByEmail method");
		System.out.println("received email is : "+email);
		
		
		Session sess=sessionFactory.getCurrentSession();
		Criteria crt= sess.createCriteria(Customer.class);
		crt.add(Restrictions.eq("email", email));
		Customer c=(Customer)crt.uniqueResult();
		System.out.println(c.getScores());
		
		System.out.println("*************below this************");
		System.out.println("email:"+ c.getEmail());
		List<Score> s=c.getScores();
		System.out.println("************above this****************");
		//sess.;
		
		
//		SessionFactory sf=new Configuration().configure().buildSessionFactory();
//		Session sess=sf.openSession();
//		sess.beginTransaction();
//		Criteria crt=sess.createCriteria(Customer.class);
//		crt.add(Restrictions.eq("email", email));
//		Customer c=(Customer)crt.uniqueResult();
//		
//		sess.getTransaction().commit();
//		sess.close();
//		
		
		return c;
		
	}

}
