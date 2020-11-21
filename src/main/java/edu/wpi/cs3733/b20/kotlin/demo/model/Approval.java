package edu.wpi.cs3733.b20.kotlin.demo.model;

public class Approval {
	
	String choiceUUID;
	User user;
	
	public Approval (String choiceUUID, User user) {
		this.choiceUUID = choiceUUID;
		this.user = user;
	}
	
	public User getApproval() {
		return this.user;
	}
}
