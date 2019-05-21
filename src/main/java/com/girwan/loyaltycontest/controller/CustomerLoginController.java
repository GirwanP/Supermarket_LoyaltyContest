package com.girwan.loyaltycontest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.girwan.loyaltycontest.dao.CustomerDao;

@Controller
//@RequestMapping("/customer")
public class CustomerLoginController {
	
	//@Autowired
	private CustomerDao cdao;
	
	@RequestMapping(value="/customerlogin",method=RequestMethod.GET)
	public String getLoginForm(){
		
		return "customerLogin";
	}
	
	@RequestMapping(value="/customerlogin",method=RequestMethod.POST)
	public String login(HttpSession session){
		
		return "customerPortal";
		
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		
		session.invalidate();
		return "customerLogin";
	}
}
