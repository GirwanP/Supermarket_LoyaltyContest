package com.girwan.loyaltycontest.services;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import com.girwan.loyaltycontest.dao.TestDaoImpl;
import com.girwan.loyaltycontest.model.Score;

public class PointsUpdaterService {
	@Autowired
	private TestDaoImpl cdao;
	
	public boolean updatePoint(int points,String userEmail){
		Score s=new Score();
		s.setCheckinDate(new Timestamp(new Date().getTime()));
		s.setPoints(points);
		cdao.updateScore(userEmail,s);
		
		return true;
	}
}
