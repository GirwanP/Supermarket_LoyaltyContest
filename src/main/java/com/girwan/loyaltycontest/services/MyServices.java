package com.girwan.loyaltycontest.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.girwan.loyaltycontest.dao.CustomerDao;
import com.girwan.loyaltycontest.dao.CustomerDaoImpl;
import com.girwan.loyaltycontest.dao.TestDao;
import com.girwan.loyaltycontest.dao.TestDaoImpl;
import com.girwan.loyaltycontest.model.Customer;
import com.girwan.loyaltycontest.model.Score;
@Service
public class MyServices {
	
	@Autowired
	private CustomerDao cdao;
//	private TestDaoImpl cdao=new TestDaoImpl();
//	@Autowired
//	@Inject
	//private CustomerDao cdao; //= new CustomerDaoImpl();
	
	//@Transactional
	public List<Score> getSortedList(String cEmail){
		
		
		System.out.println(cEmail+"  --from getsortedList method-Myservices class");
		Customer cus=cdao.getByEmail(cEmail);
		
		List<Score> sList=cus.getScores();
		for(Score ss:sList){
			System.out.println(ss.getCheckinDate());
		}
		Collections.sort(sList, new Comparator<Score>() {
			@Override
			public int compare(Score arg0, Score arg1) {
				
				return arg1.getCheckinDate().compareTo(arg0.getCheckinDate());
			}
		});
		return sList;
	}

}
