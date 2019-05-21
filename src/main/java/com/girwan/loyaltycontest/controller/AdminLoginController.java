package com.girwan.loyaltycontest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminLoginController {
	
	@RequestMapping(value="/adminlogin",method=RequestMethod.GET)
	public String getLogin(){
		
		return "adminLogin";
	}
	
	@RequestMapping(value="/adminlogin",method=RequestMethod.POST)
	public String login(){
		
		return "adminPortal";
		//return "adminLogin";
	}
}
