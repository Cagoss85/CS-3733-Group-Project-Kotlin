package edu.wpi.cs3733.b20.kotlin.demo.model;

public class Disapproval {
	User user;
	
	public Disapproval (User user) {
		this.user = user;
	}
	
	public User getDisapproval() {
		return this.user;
	}
	
}
