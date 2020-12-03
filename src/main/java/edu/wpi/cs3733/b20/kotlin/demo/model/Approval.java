package edu.wpi.cs3733.b20.kotlin.demo.model;

public class Approval {
	
	String choiceUUID;
	String username;	
	int altID;
	
	public Approval (int altID, String choiceUUID, String user) {
		this.choiceUUID = choiceUUID;
		this.username = user;
		this.altID =altID;
	}
	
	public String getUsername() {return this.username;}
	public String getChoiceUUID() {return this.choiceUUID;}
	public int getAltID() {return this.altID;}
}
