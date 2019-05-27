package com.girwan.loyaltycontest.controller;

import java.awt.Color;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.girwan.loyaltycontest.dao.CustomerDao;
import com.girwan.loyaltycontest.model.Customer;
import com.girwan.loyaltycontest.model.Score;

@Controller
// @RequestMapping("/customer")
public class CustomerLoginController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerLoginController.class);

	@Autowired
	private CustomerDao cdao;

	@RequestMapping(value = "/customerlogin", method = RequestMethod.GET)
	public String getLoginForm(HttpSession session) {
		if (!StringUtils.isEmpty(session.getAttribute("activeuserEmail"))) {

			logger.info("Acoount was already logged in");
		}
		return "customerLogin";
	}

	@RequestMapping(value = "/customerlogin", method = RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute Customer c, Model model) {
		logger.info("submitted data:" + c.getEmail() + c.getPassword());
		if (cdao.login(c.getEmail(), c.getPassword())) {
			logger.info("login successful");

			session.setAttribute("activeuserEmail", c.getEmail());
			session.setMaxInactiveInterval(150);

			// model.addAttribute("cusotmer",c.getUserName());
			model.addAttribute("customer", cdao.getByEmail(c.getEmail()));
			List<Score> scores = cdao.getByEmail(session.getAttribute("activeuserEmail").toString()).getScores();

			Collections.sort(scores, new Comparator<Score>() {
				// @Override
				public int compare(Score s1, Score s2) {
					return s2.getCheckinDate().compareTo(s1.getCheckinDate());
				}
			});

			long tPeriod = 2*60000;
			boolean aNClaimed = true;
			long diff=(new java.util.Date().getTime()-((Score)scores.toArray()[0]).getCheckinDate().getTime() );
			logger.info(Long.toString(diff*(1)));
			System.out.println(tPeriod);
			if (diff<=tPeriod) {
				System.out.println("already claimed point condition check:passed");
				aNClaimed = false;
			}

			model.addAttribute("aNClaimed", aNClaimed);
			model.addAttribute("scoreList", scores);
			// System.out.println(score);
			return "customerPortal";
		}
		logger.info("Login failed");
		model.addAttribute("error", "Invalid Username Or password");
		return "customerLogin";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.invalidate();
		return "customerLogin";
	}
}
