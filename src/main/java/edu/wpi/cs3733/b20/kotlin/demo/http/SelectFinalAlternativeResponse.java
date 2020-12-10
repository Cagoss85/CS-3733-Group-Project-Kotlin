package edu.wpi.cs3733.b20.kotlin.demo.http;


public class SelectFinalAlternativeResponse {
	public String uniqueID;

	public int altID;
	public String error;
	public int statusCode;
	
	public SelectFinalAlternativeResponse(String uuid, int altID) {
		this.uniqueID = uuid;
		
		this.altID = altID;
		this.error = "";
		this.statusCode = 200;
	}
	public SelectFinalAlternativeResponse(String uuid, int altID, String error) {
		this.uniqueID = uuid;
		
		this.altID = altID;
		this.error = error;
		this.statusCode = 400;
	}
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + uniqueID + ")";
	}
	public int getStatusCode() {return this.statusCode;}
	public void setStatusCode(int code) {this.statusCode = code;}
	
	public String getError(	) {return this.error;}
	public void setError(String er) {this.error = er;}
	
	public String getUniqueID()	{return this.uniqueID;}
	public void setUniqueID(String id) {this.uniqueID = id;}
}
