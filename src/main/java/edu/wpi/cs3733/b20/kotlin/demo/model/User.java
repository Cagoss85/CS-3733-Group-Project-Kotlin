package edu.wpi.cs3733.b20.kotlin.demo.model;

public class User {
	final String choiceUUID;
	String username;
	String password;
	
	public User(String choiceUUID, String username, String password) {  //constructor with password
		this.choiceUUID = choiceUUID;
		this.username = username;
		this.password = password;
		if(this.password.equals("")) {
			this.password=null;
		}
	}
	
	public User(String choiceUUID, String username) {   //constructor without password
		this.choiceUUID = choiceUUID;
		this.username = username;
		this.password = null;
	}
	
	public User(String choiceUUID) {
		this.choiceUUID = choiceUUID;
	}
	
	public String getChoiceUUID() {return choiceUUID;}
	
	public String getUsername() {return username;}
	
	public String getPassword() {return password;}
	
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
}
