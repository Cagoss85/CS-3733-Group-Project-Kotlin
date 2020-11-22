package edu.wpi.cs3733.b20.kotlin.demo.http;

public class AuthenticateUserResponse {
	public final int statusCode;
	public final String error;
	public final String username;
	
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
	
	public String toString() {
		return "Response(" + statusCode + "," + error + "," + username + ")";
	}
}
