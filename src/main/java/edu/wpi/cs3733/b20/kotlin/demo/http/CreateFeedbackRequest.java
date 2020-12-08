package edu.wpi.cs3733.b20.kotlin.demo.http;

public class CreateFeedbackRequest {
	public String choiceUUID;
	public int altID;
	public String username;
	public String text;
	
	public CreateFeedbackRequest(String choiceUUID, int altID, String username, String text) {
		this.choiceUUID = choiceUUID;
		this.altID = altID;
		this.username = username;
		this.text = text;
	}
	
	public CreateFeedbackRequest() {}
	
	public String getChoiceUUID() {return choiceUUID;}
	public int getAltID() {return altID;}
	public String getUsername() {return username;}
	public String getText() {return text;}

}
