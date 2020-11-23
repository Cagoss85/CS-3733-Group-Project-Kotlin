package edu.wpi.cs3733.b20.kotlin.demo.http;

public class AuthenticateUserResponse {
	public int statusCode;
	public String error;
	public String username;
	
	public AuthenticateUserResponse(String username) {
		this.username = username;
		this.error = " ";
		this.statusCode = 200;
	}
	
	public AuthenticateUserResponse(String username, String error) {
		this.username = username;
		this.error = error;
		this.statusCode = 400;
	}
	
	public AuthenticateUserResponse() {
	}
	
	public int getStatusCode() {return this.statusCode;}
	public void setStatusCode(int code) {this.statusCode = code;}
	
	public String getError() {return this.error;}
	public void setError(String er) {this.error = er;}
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + username + ")";
	}
}
