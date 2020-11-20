package edu.wpi.cs3733.b20.kotlin.demo.model;

public class Approvals {
	User user;
	
	public Approvals (User user) {
		this.user = user;
	}
	
	public User getApproval() {
		return this.user;
	}
}
