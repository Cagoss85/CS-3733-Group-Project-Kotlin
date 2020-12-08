package edu.wpi.cs3733.b20.kotlin.demo.model;


public class Feedback {
	String choiceUUID;
	int altID;
	String username;
	long timestamp;
	String text;
	
	String timestampString;
	
	public Feedback (String choiceUUID, int altID, String username, String text) {
		this.choiceUUID = choiceUUID;
		this.username = username;
		this.timestamp = System.currentTimeMillis();
		this.text = text;
	}
	
	public Feedback() {}
	
	public String getChoiceUUID() {return choiceUUID;}
	public int getAltID() {return altID;}
	public String getUsername() {return username;}
	public long getTimestamp() {return timestamp;}
	public String getText() {return text;}
	
	public String getTimestampString()	{return timestampString;}
	
	public boolean equals(Feedback feedback) {
		if (feedback.getAltID() == altID && feedback.getChoiceUUID() == choiceUUID && feedback.getUsername() == username && feedback.getTimestamp() == timestamp && feedback.getText() == text) {
			return true;}
		return false;
	}

}
