package edu.wpi.cs3733.b20.kotlin.demo.http;

public class CreateChoiceResponse {
	public final int statusCode;
	public final String error;
	public final String uniqueID;
	
	public CreateChoiceResponse(String uniqueID) {
		this.uniqueID = uniqueID;
		this.error = "";
		this.statusCode = 200;
	}
	
	public CreateChoiceResponse(String uniqueID, String error) {
		this.uniqueID = uniqueID;
		this.error = error;
		this.statusCode = 400;
	}
	
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + uniqueID + ")";
	}
}
