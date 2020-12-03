package edu.wpi.cs3733.b20.kotlin.demo.model;

public class Approval {
	
	String choiceUUID;
	String username;	
	int altID;
	
	public Approval (String choiceUUID, int altID, String user) {
		this.choiceUUID = choiceUUID;
		this.altID =altID;
		this.username = user;
	}
	
	public String getChoiceUUID() {return this.choiceUUID;}
	public String getUsername() {return this.username;}
	public int getAltID() {return this.altID;}
	
	public boolean equals(Approval approval) {
		if (approval.getAltID() == altID && approval.getChoiceUUID() == choiceUUID && approval.getUsername() == username) {
			return true;}
		return false;
	}
}
