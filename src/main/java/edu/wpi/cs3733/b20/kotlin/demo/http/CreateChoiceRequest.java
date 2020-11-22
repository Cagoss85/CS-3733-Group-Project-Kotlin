package edu.wpi.cs3733.b20.kotlin.demo.http;

import java.util.List;

import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

public class CreateChoiceRequest {
	
	public List<Alternative> alternatives;
	public int users;
	public String description;
	
	public List<Alternative> getAlternatives(){
		return alternatives;
	}
	
	public void setAlternatives(List<Alternative> alternatives) {
		this.alternatives = alternatives;
	}
	
	public int getUsers() {
		return users;
	}
	
	public void setUsers(int users) {
		this.users = users;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CreateChoiceRequest() {
		
	}
	
	public CreateChoiceRequest(List<Alternative> alternatives, int users, String description) {
		this.alternatives = alternatives;
		this.users = users;
		this.description = description;
	}
	
	public String toString() {
		return "CreateChoice(with array, " + users + "," + description + ")";
	}

}
