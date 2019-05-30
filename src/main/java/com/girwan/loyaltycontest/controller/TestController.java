package com.girwan.loyaltycontest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.girwan.loyaltycontest.dao.TestDaoImpl;
import com.girwan.loyaltycontest.model.Score;
import com.girwan.loyaltycontest.services.MyConstants;
import com.girwan.loyaltycontest.services.MyServices;
import com.girwan.loyaltycontest.services.PointsUpdaterService;

@Controller
public class TestController {
	
	@RequestMapping(value="/testC",method=RequestMethod.GET)
	public String getCusPortal(HttpSession session,Model model){
		Object activeuserEmail=session.getAttribute("activeuserEmail");
		if(org.springframework.util.StringUtils.isEmpty(activeuserEmail)){
			return "customerLogin";
		}
		
		boolean daNClaimed=true,waNClaimed=true,maNClaimed=true;
		
	//	model.addAttribute("customer", cdao.getByEmail(session.getAttribute("activeuserEmail").toString()));
		model.addAttribute("daNClaimed", daNClaimed);
		model.addAttribute("waNClaimed",waNClaimed);
		model.addAttribute("maNClaimed", maNClaimed);
		//scores.add(arg0)
		model.addAttribute("scoreList", new MyServices().getSortedList(activeuserEmail.toString()));
		
		return "customerPortal";
	}
}
