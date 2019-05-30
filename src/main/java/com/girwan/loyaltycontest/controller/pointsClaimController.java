package com.girwan.loyaltycontest.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.girwan.loyaltycontest.services.PointsUpdaterService;

@Controller
public class pointsClaimController {
	private static final Logger logger = LoggerFactory.getLogger(pointsClaimController.class);

	@Autowired
	private CustomerDao cdao;

	@RequestMapping(value = "/getPoints", method = RequestMethod.GET)
	public String claimGet(HttpServletRequest req, HttpSession session, Model model, HttpServletResponse res) {
		System.out.println("inside claimpoint daily get-request");
		// return claimPoints(session,req, res,model);
		if (org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))) {
			session.invalidate();
			return "customerLogin";
		}

		List<Score> scores = cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();

		// int l=scores.size();

		Collections.sort(scores, new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {
				return s2.getCheckinDate().compareTo(s1.getCheckinDate());
			}
		});

		long tPeriod =MyConstants.dayLength;
		
		boolean daNClaimed = true;
		boolean maNClaimed=true;
		boolean waNClaimed=true;
		
		long diff = (new java.util.Date().getTime() - ((Score) scores.toArray()[0]).getCheckinDate().getTime());
		logger.info(Long.toString(diff));

		System.out.println(tPeriod);
		if (diff >= tPeriod) {
			System.out.println("already claimed point condition check:passed");
			daNClaimed = false;

			Score score = new Score();
			score.setCheckinDate(new Timestamp(new java.util.Date().getTime()));
			score.setPoints(MyConstants.dailyPoint);
			cdao.updateScore(session.getAttribute("activeuserEmail").toString(), score);

			scores.add(0, score);

		}
		//
		// for (Score s : scores) {
		//
		// System.out.println(s);
		// }
		model.addAttribute("customer", cdao.getByEmail(session.getAttribute("activeuserEmail").toString()));
		// model.addAttribute("activeuserEmail",session.getAttribute("activeuserEmail").toString());
		model.addAttribute("daNClaimed", daNClaimed);
		model.addAttribute("waNClaimed", waNClaimed);
		model.addAttribute("maNClaimed", maNClaimed);
		model.addAttribute("scoreList", scores);
		return "customerPortal";
	}

	@RequestMapping(value = "/getPoints", method = RequestMethod.POST)
	public String claimPoints(HttpSession session, HttpServletRequest req, HttpServletResponse res, Model model) {
		System.out.println("inside claimpoint daily post-method");
		if (org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))) {
			session.invalidate();
			return "customerLogin";
		}

		List<Score> scores = cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();

		// int l=scores.size();

		Collections.sort(scores, new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {
				return s2.getCheckinDate().compareTo(s1.getCheckinDate());
			}
		});

		long tPeriod = 1 * 6000L;
		
		boolean daNClaimed = true;
		boolean maNClaimed=true;
		boolean waNClaimed=true;
		
		
		if ((new java.util.Date().getTime() - ((Score) scores.toArray()[0]).getCheckinDate().getTime()) >= tPeriod) {
			System.out.println("already not claimed point condition check:passed");
			// aNClaimed = true;

			Score score = new Score();
			score.setCheckinDate(new Timestamp(new java.util.Date().getTime()));
			score.setPoints(MyConstants.dailyPoint);
			cdao.updateScore(session.getAttribute("activeuserEmail").toString(), score);

		}

		for (Score s : scores) {

			System.out.println(s);
		}
		model.addAttribute("daNClaimed", daNClaimed);
		model.addAttribute("scoreList", scores);
		model.addAttribute("waNClaimed", waNClaimed);
		model.addAttribute("maNClaimed", maNClaimed);
		return "customerPortal";
	}

	//methods below are incomplete
	
	
	@RequestMapping(value = "/claimWeekly", method = RequestMethod.POST)
	public String claimWeeklyPt(HttpSession session, Model model) {
		logger.info("claim weakly post method");
		if (org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))) {
			session.invalidate();
			return "customerLogin";
		}

		List<Score> scores = cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();

		Collections.sort(scores, new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {
				return s2.getCheckinDate().compareTo(s1.getCheckinDate());
			}
		});

		long tPeriod = MyConstants.dayLength;
		boolean daNClaimed=true;
		boolean maNClaimed=true;
		boolean waNClaimed=true;

		model.addAttribute("waNClaimed", waNClaimed);
		model.addAttribute("maNClaimed", maNClaimed);
		
		
		long diff = (new java.util.Date().getTime() - ((Score) scores.toArray()[0]).getCheckinDate().getTime());
		logger.info(Long.toString(diff));

		System.out.println(tPeriod);
		if (diff >= tPeriod) {
			System.out.println("already claimed point condition check:passed");
			daNClaimed = false;

			Score score = new Score();
			score.setCheckinDate(new Timestamp(new java.util.Date().getTime()));
			score.setPoints(MyConstants.weeklyPoint);
			cdao.updateScore(session.getAttribute("activeuserEmail").toString(), score);

			scores.add(0, score);

		}

		model.addAttribute("customer", cdao.getByEmail(session.getAttribute("activeuserEmail").toString()));
		// model.addAttribute("activeuserEmail",session.getAttribute("activeuserEmail").toString());
		model.addAttribute("daNClaimed", daNClaimed);
		model.addAttribute("scoreList", scores);
		return "customerPortal";
	}

	@RequestMapping(value = "/claimMonthly", method = RequestMethod.POST)
	public String claimMonthly(HttpSession session, Model model) {

		if (org.springframework.util.StringUtils.isEmpty(session.getAttribute("activeuserEmail"))) {
			return "customerLogin";
		}

		List<Score> scores = new MyServices().getSortedList(session.getAttribute("activeuserEmail").toString());
		
		
		boolean alreadyClaimed = true;
		boolean daNClaimed=true;
		boolean waNClaimed=true;
		
		model.addAttribute("customer", cdao.getByEmail(session.getAttribute("activeuserEmail").toString()));
		model.addAttribute("daNClaimed", daNClaimed);
		model.addAttribute("waNClaimed",waNClaimed);
		
		if (!alreadyClaimed) {
			System.out.println("implement the claim validity logic here");
			model.addAttribute("maNClaimed", true);
			model.addAttribute("scoreList", scores);
			return "customerPortal";
		}
		
		boolean status = new PointsUpdaterService().updatePoint(MyConstants.monthlyPoint,
				session.getAttribute("activeuserEmail").toString());
		System.out.println(status);
		
		model.addAttribute("maNClaimed", alreadyClaimed);
		//scores.add(arg0)
		model.addAttribute("scoreList", scores);
		
		return "customerPortal";
	}
}
