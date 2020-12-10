package edu.wpi.cs3733.b20.kotlin.demo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Alternative{
	public String description;
	public ArrayList<Feedback> feedback = new ArrayList<>();
	public ArrayList<Approval> approvals = new ArrayList<>();;
	public ArrayList<Disapproval> disapprovals = new ArrayList<>();
	
	//default constructor to make AWS happy
	public Alternative() {}
	
	//Default constructor
	public Alternative(String description) {
		this.description = description;
	}
	
	//Constructor for get choice
	public Alternative(String description, ArrayList<Feedback> feedback, ArrayList<Approval> approvals, ArrayList<Disapproval> disapprovals) {
		this.description = description;
		this.feedback = feedback;
		this.approvals = approvals;
		this.disapprovals = disapprovals;
	}
	//add Feedback to an alternative
	public void addFeedback(Feedback f) {
		feedback.add(f);
	}
	
	//add approval to an alternative
	public void addApproval(Approval a) {
		approvals.add(a);
	}
	
	//remove an approval to an alternative
	public void removeApproval(Approval a) {
		approvals.remove(a);
	}
	
	//add disapproval to an alternative
	public void addDisapproval(Disapproval d) {
		disapprovals.add(d);
	}
	
	//remove a disapproval from an alternative
	public void removeDisapproval(Disapproval d) {
		disapprovals.remove(d);
	}
	
	//getters and setters for the description
	public String getDescription()	{return description;}
	public void setDescription(String text) {this.description = text;}
	
	//getters and setters for the feedback
	public ArrayList<Feedback> getFeedback() {return feedback;}
	
	//getters and setters for the approvals
	public ArrayList<Approval> getApprovals() {return approvals;}
	
	//getters and setters for the disapprovals
	public ArrayList<Disapproval> getDisapprovals(){return disapprovals;}
}
