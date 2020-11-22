package edu.wpi.cs3733.b20.kotlin.demo.model;

import java.util.ArrayList;

public class Choice {
	private String uuid;
	private ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
	private int maxUsers;
	private String description;
	private long timeCreated;
	ArrayList<User> users = new ArrayList<User>();
	
	public Choice(String uuid,ArrayList<Alternative> alternatives, int maxUsers, String description){
		this.uuid= uuid;
		this.setAlternatives(alternatives);
		this.setMaxUsers(maxUsers);
		this.setTimeCreated(System.currentTimeMillis());
		this.description = description;
	}
	
	boolean checkUserPassword(User user, String password) {
		 return (user.getPassword().contentEquals(password));
	}
	
	boolean addUser(User user) {
		return users.add(user);
	}

	public String getUuid() {return uuid;}
	//public void setUuid(String uuid) {this.uuid = uuid;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public int getMaxUsers() {return maxUsers;}
	public void setMaxUsers(int maxUsers) {this.maxUsers = maxUsers;}

	public long getTimeCreated() {return timeCreated;}
	public void setTimeCreated(long timeCreated) {this.timeCreated = timeCreated;}

	public ArrayList<Alternative> getAlternatives() {return alternatives;}
	public void setAlternatives(ArrayList<Alternative> alternatives) {this.alternatives = alternatives;}


}
