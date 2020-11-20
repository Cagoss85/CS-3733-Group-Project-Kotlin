package edu.wpi.cs3733.b20.kotlin.demo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Alternative{
	public String description;
	public ArrayList<Feedback> feedback = new ArrayList<>();
	public ArrayList<Approval> approvals = new ArrayList<>();;
	public ArrayList<Disapproval> disapprovals = new ArrayList<>();
	
	
	public Alternative(String description) {
		this.description = description;
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
	public void setFeedback(ArrayList<Feedback> feedback) {this.feedback = feedback;}
	
	//getters and setters for the approvals
	public ArrayList<Approvals> getApprovals() {return approvals;}
	public void setApprovals(ArrayList<Approval> approvals) {this.approvals = approvals;}
	
	//getters and setters for the disapprovals
	public ArrayList<Disapprovals> getDisapprovals(){return disapprovals;}
	public void setDisapprovals(ArrayList<Disapprovals> disapprovals) {this.disapprovals = disapprovals;}


}
