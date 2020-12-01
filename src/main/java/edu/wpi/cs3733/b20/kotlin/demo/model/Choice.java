package edu.wpi.cs3733.b20.kotlin.demo.model;

import java.util.ArrayList;

public class Choice {
	private String uuid;
	private ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
	private ArrayList<User> users = new ArrayList<User>();
	private int maxUsers;
	private String description;
	public Alternative finalAlternative;
	private long timeCreated;
	
	//Administrator Console Attributes
	public boolean isChosen;
	public String timeCreatedString;
	
	//Default Choice Constructor
	public Choice(String uuid,ArrayList<Alternative> alternatives, int maxUsers, String description){
		this.uuid= uuid;
		this.alternatives = alternatives;
		this.maxUsers = maxUsers;
		this.timeCreated = System.currentTimeMillis();
		this.description = description;
	}
	
	//Admin Console Constructor
	public Choice(String uuid, String timeCreatedString, boolean isChosen){
		this.uuid= uuid;
		this.timeCreatedString = timeCreatedString;
		this.isChosen = isChosen;
	}
	
	public Choice() {}
	
	public boolean addUser(User user) {
		return users.add(user);
	}

	public String getUuid() {return uuid;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public int getMaxUsers() {return maxUsers;}
	public void setMaxUsers(int maxUsers) {this.maxUsers = maxUsers;}

	public long getTimeCreated() {return timeCreated;}
	public void setTimeCreated(long timeCreated) {this.timeCreated = timeCreated;}
	
	public String getTimeCreatedString() {return timeCreatedString;}
	public void setTimeCreatedString(String timeCreatedString) {this.timeCreatedString = timeCreatedString;}

	public ArrayList<Alternative> getAlternatives() {return alternatives;}
	public void setAlternatives(ArrayList<Alternative> alternatives) {this.alternatives = alternatives;}

	public ArrayList<User> getUsers()	{return this.users;}
	
	public boolean getIsChosen() {return isChosen;}
	public void setIsChosen(boolean flag)	{this.isChosen = flag;}
}
