package edu.wpi.cs3733.b20.kotlin.demo.model;

public class User {
	
	public final String choiceUUID;
	public final String username;
	public final String password;
	public final boolean hasPassword;
	
	public User(String choiceUUID, String username, String password) {  //constructor with password
		this.choiceUUID = choiceUUID;
		this.username = username;
		this.password = password;
		this.hasPassword = true;
	}
	
	public User(String choiceUUID, String username) {   //constructor without password
		this.choiceUUID = choiceUUID;
		this.username = username;
		this.password = null;
		this.hasPassword = false;
	}
	
	public String getChoiceUUID() {
		return choiceUUID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isHasPassword() {
		return hasPassword;
	}
	
	public boolean equals(Object o) {
		if (o==null) {
			return false;
		} else if(o instanceof User) {
			User otherUser = (User) o;
			return (choiceUUID.equals(otherUser.getChoiceUUID()) && username.equals(otherUser.getUsername()));
		} else {
			return false;
		}
	}
	
	public String toString() {
		String outString = "";
		if(hasPassword) {
			outString = "Choice UUID: " + choiceUUID + " Username: " + username + " Password: " + password;
		} else {
			outString = "Choice UUID: " + choiceUUID + " Username: " + username + " No password.";
		}
		return outString;
	}

}
