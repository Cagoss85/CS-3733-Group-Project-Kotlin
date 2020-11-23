package edu.wpi.cs3733.b20.kotlin.demo.http;

public class CreateUserRequest {
	public String choiceUUID;
	public String username;
	public String password;

	public CreateUserRequest(String choiceUUID, String username, String password) {
		this.choiceUUID = choiceUUID;
		this.username = username;
		this.password = password;
	}
	
	public CreateUserRequest(String choiceUUID, String username) {
		this.choiceUUID = choiceUUID;
		this.username = username;
	}
	
	public CreateUserRequest() {
	}

	public String getChoiceUUID() {return choiceUUID;}
	public void setChoiceUUID(String id) {this.choiceUUID = id;}

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	public String toString() {
			return "CreateUser(" + choiceUUID  + "," + username + "," + password + ")";
	}
}