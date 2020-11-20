package edu.wpi.cs3733.b20.kotlin.demo.model;


public class Feedback {
	User user;
	long timestamp;
	String text;
	
	//UML said to input time but that seems wrong
	public Feedback (User user, String text) {
		this.user = user;
		this.timestamp = System.currentTimeMillis();
		this.text = text;
	}
	
	
}
