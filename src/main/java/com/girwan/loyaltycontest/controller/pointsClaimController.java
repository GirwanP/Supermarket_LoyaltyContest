package com.girwan.loyaltycontest.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.metamodel.relational.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.girwan.loyaltycontest.dao.CustomerDao;
import com.girwan.loyaltycontest.model.Score;

@Controller
public class pointsClaimController {
	private static final Logger logger =LoggerFactory.getLogger(pointsClaimController.class);
	
	@Autowired
	private CustomerDao cdao;

	@RequestMapping(value = "/getPoints", method = RequestMethod.GET)
	public String claimGet(HttpServletRequest req, HttpSession session, Model model, HttpServletResponse res) {
		System.out.println("inside claimpoint");
		// return claimPoints(session,req, res,model);
		if(org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))) {
			session.invalidate();
			return "customerLogin";
		}
		
		List<Score> scores = cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();

		// int l=scores.size();

		Collections.sort(scores, new Comparator<Score>(){
			@Override
			public int compare(Score s1, Score s2) {
				return s2.getCheckinDate().compareTo(s1.getCheckinDate());
			}
		});
		
		long tPeriod=2*60000;
		boolean aNClaimed = false;
		long diff=(new java.util.Date().getTime()-((Score)scores.toArray()[0]).getCheckinDate().getTime() );
		logger.info(Long.toString(diff));
		System.out.println(tPeriod);
		if ( diff>=tPeriod){
			System.out.println("already claimed point condition check:passed");
			aNClaimed = false;
			
			Score score = new Score();
			score.setCheckinDate(new Date(new java.util.Date().getTime()));
			score.setPoints(30);
			cdao.updateScore(session.getAttribute("activeuserEmail").toString(), score);
			
			scores.add(0, score);
			

		}
//		
//		for (Score s : scores) {
//
//			System.out.println(s);
//		}
		model.addAttribute("customer",cdao.getByEmail(session.getAttribute("activeuserEmail").toString()));
		//model.addAttribute("activeuserEmail",session.getAttribute("activeuserEmail").toString());
		model.addAttribute("aNClaimed",aNClaimed);
		model.addAttribute("scoreList", scores);
		return "customerPortal";
	}

	@RequestMapping(value = "/getPoints", method = RequestMethod.POST)
	public String claimPoints(HttpSession session, HttpServletRequest req, HttpServletResponse res, Model model) {

		if (org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))) {
			session.invalidate();
			return "customerLogin";
		}
		
		List<Score> scores = cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();

		// int l=scores.size();

		Collections.sort(scores, new Comparator<Score>(){
			@Override
			public int compare(Score s1, Score s2) {
				return s2.getCheckinDate().compareTo(s1.getCheckinDate());
			}
		});
		
		long tPeriod=2*6000L;
		boolean aNClaimed =false ;
		if (  (new java.util.Date().getTime()-((Score)scores.toArray()[0]).getCheckinDate().getTime() )>=tPeriod){
			System.out.println("already not claimed point condition check:passed");
			//aNClaimed = true;
			
			Score score = new Score();
			score.setCheckinDate(new Date(new java.util.Date().getTime()));
			score.setPoints(100);
			cdao.updateScore(session.getAttribute("activeuserEmail").toString(), score);

			
		}
		
		for (Score s : scores) {

			System.out.println(s);
		}
		model.addAttribute("aNClaimed",aNClaimed);
		model.addAttribute("scoreList", scores);
		return "customerPortal";
	}
}
