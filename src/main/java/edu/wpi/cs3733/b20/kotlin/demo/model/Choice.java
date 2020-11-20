package edu.wpi.cs3733.b20.kotlin.demo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Choice {
	String uuid;
	ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
	int maxUsers;
	String description;
	long timeCreated;
	ArrayList<User> users = new ArrayList<User>();
	
	Choice(String uuid,ArrayList<Alternative> alternatives, int maxUsers, String description){
		this.uuid = uuid;
		this.alternatives = alternatives;
		this.maxUsers = maxUsers;
		this.timeCreated = System.currentTimeMillis();
		}
	boolean checkUserPassword(User user, String password) {
		 return (user.getPassword().contentEquals(password));
	}
	boolean addUser(User user) {
		return users.add(user);
		
	}
	

}
