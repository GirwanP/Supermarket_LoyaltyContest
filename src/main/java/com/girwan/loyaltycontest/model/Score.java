package com.girwan.loyaltycontest.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Scores")
public class Score {
	@Id
	@GeneratedValue
	private int id;
	private Timestamp checkinDate;
	private int points;
	
	public Timestamp getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Timestamp checkinDate) {
		this.checkinDate = checkinDate;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
}
