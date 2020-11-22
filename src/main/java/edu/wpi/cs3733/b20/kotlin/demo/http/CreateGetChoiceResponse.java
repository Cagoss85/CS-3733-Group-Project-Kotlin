package edu.wpi.cs3733.b20.kotlin.demo.http;


public class CreateGetChoiceResponse {
	public final String uniqueID;
	public final String description;
	public final int user;
	public final String alternatives;
	public final String finalAlternative;
	public final int statusCode;
	public final String error;
	
	public CreateGetChoiceResponse(String uniqueID, String alternatives, int user, String description, String finalAlternative) {
		this.uniqueID = uniqueID;
		this.alternatives = alternatives;
		this.user = user;
		this.description = description;
		this.finalAlternative = finalAlternative;
		this.error = "";
		this.statusCode = 200;
	}
	
	public CreateGetChoiceResponse(String uniqueID, String alternatives, int user, String description, String finalAlternative, String error) {
		this.uniqueID = uniqueID;
		this.alternatives = alternatives;
		this.user = user;
		this.description = description;
		this.finalAlternative = finalAlternative;
		this.error = "error";
		this.statusCode = 400;
	}
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + uniqueID + ")";
	}
}
