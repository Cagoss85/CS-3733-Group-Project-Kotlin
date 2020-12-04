package edu.wpi.cs3733.b20.kotlin.demo.http;

public class CreateApprovalRequest {
	public int altID;
	public String choiceUUID;
	public String username;
	
	public CreateApprovalRequest(int altID, String choiceUUID, String username){
		this.altID = altID;
		this.choiceUUID = choiceUUID;
		this.username = username;
	}
	public CreateApprovalRequest() {}
	
	public int getAltID() {return altID;}
	public String getChoiceUUID() {return choiceUUID;}
	public String getUsername() {return username;}
	
	public String toString() {
		return "CreateApproval(with altID, " + altID + " choiceUUID, " + choiceUUID + " username, " + username;
	}
	
	
}
