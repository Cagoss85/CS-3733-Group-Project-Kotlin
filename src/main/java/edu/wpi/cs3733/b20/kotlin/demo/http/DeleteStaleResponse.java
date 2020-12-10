package edu.wpi.cs3733.b20.kotlin.demo.http;

public class DeleteStaleResponse {
	public String error;
	public int statusCode;
	
	public DeleteStaleResponse() {
		this.statusCode = 200;
		this.error = "";
	}
	
	public DeleteStaleResponse(String error, int statusCode) {
		this.error = error;
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {return this.statusCode;}
	public void setStatusCode(int code) {this.statusCode = code;}
	
	public String getError(	) {return this.error;}
	public void setError(String er) {this.error = er;}
	
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + ")";
	}
}
