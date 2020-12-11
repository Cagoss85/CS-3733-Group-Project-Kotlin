package edu.wpi.cs3733.b20.kotlin.demo.model;

import java.util.ArrayList;

public class Choice {
	private String uuid;
	private ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
	private ArrayList<User> users = new ArrayList<User>();
	private int maxUsers;
	private String description;
	private int finalAlternative;
	private boolean isChosen;
	private long timeCreated;
	
	//Administrator Console Attributes
	public String timeCreatedString;
	
	//default choice constructor for creating choice
	public Choice(String uuid,ArrayList<Alternative> alternatives, int maxUsers, String description){
		this.uuid= uuid;
		this.alternatives = alternatives;
		this.maxUsers = maxUsers;
		this.timeCreated = System.currentTimeMillis();
		this.description = description;
	}
	
	//Choice constructor with final alternative and isclosed
	public Choice(String uuid,ArrayList<Alternative> alternatives, int maxUsers, String description, int finalAlternative, boolean isChosen){
		this.uuid= uuid;
		this.alternatives = alternatives;
		this.maxUsers = maxUsers;
		this.timeCreated = System.currentTimeMillis();
		this.description = description;
		this.finalAlternative = finalAlternative;
		this.isChosen = isChosen;
	}
	
	//Admin Console Constructor
	public Choice(String uuid, String description, String timeCreatedString, boolean isChosen){
		this.uuid= uuid;
		this.description = description;
		this.timeCreatedString = timeCreatedString;
		this.isChosen = isChosen;
	}
	
	public Choice() {}
	
	public boolean addUser(User user) {
		return users.add(user);
	}

	public String getUuid() {return uuid;}
	
	public ArrayList<Alternative> getAlternatives() {return alternatives;}
	public void setAlternatives(ArrayList<Alternative> alternatives) {this.alternatives = alternatives;}
	
	public ArrayList<User> getUsers()	{return this.users;}
	
	public int getMaxUsers() {return maxUsers;}
	public void setMaxUsers(int maxUsers) {this.maxUsers = maxUsers;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public long getTimeCreated() {return timeCreated;}
	public void setTimeCreated(long timeCreated) {this.timeCreated = timeCreated;}
	
	public String getTimeCreatedString() {return timeCreatedString;}
	public boolean getIsChosen() {return isChosen;}
	
	public int getFinalAlternative() {return finalAlternative;}
}
