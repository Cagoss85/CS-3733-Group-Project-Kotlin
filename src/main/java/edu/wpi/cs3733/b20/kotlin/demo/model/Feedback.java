package edu.wpi.cs3733.b20.kotlin.demo.model;


public class Feedback {
	
	String choiceUUID;
	User user;
	long timestamp;
	String text;
	
	//UML said to input time but that seems wrong
	public Feedback (String choiceUUID, User user, String text) {
		this.choiceUUID = choiceUUID;
		this.user = user;
		this.timestamp = System.currentTimeMillis();
		this.text = text;
	}
	
	
}
