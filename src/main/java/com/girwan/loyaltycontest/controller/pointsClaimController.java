package com.girwan.loyaltycontest.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.girwan.loyaltycontest.dao.CustomerDao;
import com.girwan.loyaltycontest.model.Score;

@Controller
public class pointsClaimController {
	@Autowired
	private CustomerDao cdao;
	
	@RequestMapping(value="/getPoints",method=RequestMethod.GET)
	public String claimGet(HttpServletRequest req,HttpSession session,Model model,HttpServletResponse res){
		
		//return claimPoints(session,req, res,model);
		if(org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))){
			session.invalidate();
			return "cusotmerLogin";
		}
		Score score=new Score();
		score.setCheckinDate(new Date(new java.util.Date().getTime()));
		score.setPoints(10);
		cdao.updateScore(session.getAttribute("activeuserEmail").toString(), score);
		
		List<Score> scores=cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();
		model.addAttribute("scoreList",scores);
		return "customerPortal";
	}
	
	@RequestMapping(value="/getPoints",method=RequestMethod.POST)
	public String claimPoints(HttpSession session,HttpServletRequest req,HttpServletResponse res,Model model){
		
		if(org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))){
			session.invalidate();
			return "cusotmerLogin";
		}
		Score score=new Score();
		score.setCheckinDate(new Date(new java.util.Date().getTime()));
		score.setPoints(10);
		cdao.updateScore(session.getAttribute("activeuserEmail").toString(), score);
		
		List<Score> scores=cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();
		model.addAttribute("scoreList",scores);
		return "cusotomerPortal";
	}
}
