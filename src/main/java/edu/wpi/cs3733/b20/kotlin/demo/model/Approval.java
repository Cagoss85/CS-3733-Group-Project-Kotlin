package edu.wpi.cs3733.b20.kotlin.demo.model;

public class Approval {
	User user;
	
	public Approval (User user) {
		this.user = user;
	}
	
	public User getApproval() {
		return this.user;
	}
}
