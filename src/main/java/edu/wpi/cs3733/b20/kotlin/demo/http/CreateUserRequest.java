package edu.wpi.cs3733.b20.kotlin.demo.http;

public class CreateUserRequest {
	public String username;
	public String password;
	public boolean hasPassword;
	
	public CreateUserRequest(String username, String password, boolean hasPassword) {
		this.username = username;
		this.password = password;
		this.hasPassword = hasPassword;
	}
	
	public CreateUserRequest(String username, boolean hasPassword) {
		this.username = username;
		this.hasPassword = hasPassword;
	}
}
