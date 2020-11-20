package edu.wpi.cs3733.b20.kotlin.demo.model;

import java.sql.Date;

public class Feedback {
	User user;
	long timestamp;
	String text;
	
	public Feedback (User user, long timestamp, String text) {
		this.user = user;
		this.timestamp = timestamp;
		this.text = text;
	}
	
	
}
