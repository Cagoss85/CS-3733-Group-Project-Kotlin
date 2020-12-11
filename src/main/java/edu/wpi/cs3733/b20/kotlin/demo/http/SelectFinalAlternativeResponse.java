package edu.wpi.cs3733.b20.kotlin.demo.http;

//test
public class SelectFinalAlternativeResponse {
	public String choiceUUID;

	public int altID;
	public String error;
	public int statusCode;
	
	public SelectFinalAlternativeResponse(String choiceUUID, int altID) {
		this.choiceUUID = choiceUUID;
		
		this.altID = altID;
		this.error = "";
		this.statusCode = 200;
	}
	public SelectFinalAlternativeResponse(String choiceUUID, int altID, String error) {
		this.choiceUUID = choiceUUID;
		
		this.altID = altID;
		this.error = error;
		this.statusCode = 400;
	}
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + choiceUUID + ")";
	}
	public int getStatusCode() {return this.statusCode;}
	public void setStatusCode(int code) {this.statusCode = code;}
	
	public String getError(	) {return this.error;}
	public void setError(String er) {this.error = er;}
	
	public String getChoiceUUID()	{return this.choiceUUID;}
	public void setChoiceUUID(String choiceUUID) {this.choiceUUID = choiceUUID;}
}
