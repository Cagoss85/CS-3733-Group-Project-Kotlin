package edu.wpi.cs3733.b20.kotlin.demo.model;

public class Disapproval {
	
	String choiceUUID;
	User user;
	
	public Disapproval (String choiceUUID, User user) {
		this.choiceUUID = choiceUUID;
		this.user = user;
	}
	
	public User getDisapproval() {
		return this.user;
	}
	
}
