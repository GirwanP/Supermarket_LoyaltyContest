package com.girwan.loyaltycontest.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.girwan.loyaltycontest.dao.CustomerDao;
import com.girwan.loyaltycontest.model.Score;
import com.girwan.loyaltycontest.services.MyConstants;
import com.girwan.loyaltycontest.services.MyServices;

@Controller
public class CustomerPortalController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerLoginController.class);

	@Autowired
	private CustomerDao cdao;
	
	
	// use return "redirect:getCustomerPortal" ;  to send this page after login/updates
	@RequestMapping(value="/getCustomerPortal",method=RequestMethod.GET)
	public String getCusPortal(HttpSession session,Model model){
		Object activeuserEmail=session.getAttribute("activeuserEmail");
		if(org.springframework.util.StringUtils.isEmpty(activeuserEmail)){
			return "customerLogin";
		}
		
		boolean daNClaimed=true,waNClaimed=true,maNClaimed=true;
		
		List<Score> scores = cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();
//		List<Score> scores = (new MyServices()).getSortedList(session.getAttribute("activeuserEmail").toString());
			//test and remove
		
		Collections.sort(scores, new Comparator<Score>() {
			 //@Override
			public int compare(Score s1, Score s2) {
				return s2.getCheckinDate().compareTo(s1.getCheckinDate());
			}
		});
		long tPeriod =MyConstants.dayLength;
		
		long diff=(new java.util.Date().getTime()-((Score)scores.toArray()[0]).getCheckinDate().getTime() );
		logger.info(Long.toString(diff*(1)));
		System.out.println(tPeriod);
		
		if (diff<=tPeriod) {
			System.out.println("already claimed point condition check:passed");
			daNClaimed = false;
		}
		
		model.addAttribute("customer", cdao.getByEmail(session.getAttribute("activeuserEmail").toString()));
		model.addAttribute("daNClaimed", daNClaimed);
		model.addAttribute("waNClaimed",waNClaimed);
		model.addAttribute("maNClaimed", maNClaimed);
		//scores.add(arg0)
		//model.addAttribute("scoreList", new MyServices().getSortedList(activeuserEmail.toString()));
		model.addAttribute("scoreList", scores);
		
		
		return "customerPortal";
	}
}
