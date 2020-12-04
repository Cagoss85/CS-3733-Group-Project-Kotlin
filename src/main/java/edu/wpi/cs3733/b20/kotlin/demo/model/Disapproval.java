package edu.wpi.cs3733.b20.kotlin.demo.model;

public class Disapproval {
	String choiceUUID;
	String username;	
	int altID;
	
	public Disapproval (String choiceUUID, int altID, String user) {
		this.choiceUUID = choiceUUID;
		this.altID =altID;
		this.username = user;
	}
	
	public String getChoiceUUID() {return this.choiceUUID;}
	public String getUsername() {return this.username;}
	public int getAltID() {return this.altID;}
	
	public boolean equals(Disapproval disapproval) {
		if (disapproval.getAltID() == altID && disapproval.getChoiceUUID() == choiceUUID && disapproval.getUsername() == username) {
			return true;}
		return false;
	}
}
