package com.girwan.loyaltycontest.controller;



import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.girwan.loyaltycontest.dao.CustomerDao;
import com.girwan.loyaltycontest.model.Customer;

@Controller
//@RequestMapping("/customer")
public class CustomerLoginController {
	private static final Logger logger=LoggerFactory.getLogger(CustomerLoginController.class);
	
	@Autowired
	private CustomerDao cdao;
	
	@RequestMapping(value="/customerlogin",method=RequestMethod.GET)
	public String getLoginForm(){
		
		return "customerLogin";
	}
	
	@RequestMapping(value="/customerlogin",method=RequestMethod.POST)
	public String login(HttpSession session,@ModelAttribute Customer c,Model model){
		logger.info("submitted data:"+c.getEmail()+c.getPassword());
		if(cdao.login(c.getEmail(),c.getPassword())){
			logger.info("login successful");
			
			session.setAttribute("activeuser", c.getUserName());
			session.setMaxInactiveInterval(120);
			
			model.addAttribute("cusotmer",c.getUserName());
			
		return "customerPortal";
		}
		logger.info("Login failed");
		model.addAttribute("error","Invalid Username Or password");
		return "customerLogin";
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		
		session.invalidate();
		return "customerLogin";
	}
}
