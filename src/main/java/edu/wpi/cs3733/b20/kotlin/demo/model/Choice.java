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
	
	Choice(String uuid,ArrayList<Alternative> alternatives, int maxUsers, String description){
		this.uuid = uuid;
		this.alternatives = alternatives;
		this.maxUsers = maxUsers;
		this.timeCreated = System.currentTimeMillis();
		}
	

}
