package com.girwan.loyaltycontest.services;

import java.util.List;

import com.girwan.loyaltycontest.model.Score;

public class TestService {
	public static void main(String[] args) {
		MyServices ms=new MyServices();
		List<Score> list=ms.getSortedList("a@gmail.com");
		
		for(Score s:list){
			System.out.println(s.getCheckinDate()+"  points: "+s.getPoints());
		}
	}
}
