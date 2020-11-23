package edu.wpi.cs3733.b20.kotlin.demo.http;

import java.util.ArrayList;

import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

public class GetChoiceResponse {
	public final String uniqueID;
	public final ArrayList<Alternative> alternatives;
	public final int maxUsers;
	public final String description;
	public final String finalAlternative;
	
	public final int statusCode;
	public final String error;
	
	public GetChoiceResponse(String uniqueID, ArrayList<Alternative> alternatives, int user, String description, String finalAlternative) {
		this.uniqueID = uniqueID;
		this.alternatives = alternatives;
		this.maxUsers = user;
		this.description = description;
		this.finalAlternative = finalAlternative;
		this.error = "";
		this.statusCode = 200;
	}
	
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + uniqueID + ")";
	}
}
