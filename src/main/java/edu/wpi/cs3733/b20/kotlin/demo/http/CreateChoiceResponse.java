package edu.wpi.cs3733.b20.kotlin.demo.http;

public class CreateChoiceResponse {
	public int statusCode;
	public String error;
	public String uniqueID;
	
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
	
	public CreateChoiceResponse() {
	}
	
	public int getStatusCode() {return this.statusCode;}
	public void setStatusCode(int code) {this.statusCode = code;}
	
	public String getError(	) {return this.error;}
	public void setError(String er) {this.error = er;}
	
	public String getUniqueID()	{return this.uniqueID;}
	public void setUniqueID(String id) {this.uniqueID = id;}
	
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + uniqueID + ")";
	}
}
