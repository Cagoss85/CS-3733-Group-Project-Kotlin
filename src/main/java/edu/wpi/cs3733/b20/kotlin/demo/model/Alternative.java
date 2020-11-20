package edu.wpi.cs3733.b20.kotlin.demo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Alternative implements Iterable<Feedback>{
	String description;
	ArrayList<Feedback> feedback = new ArrayList<>();
	ArrayList<Approval> approvals = new ArrayList<>();;
	ArrayList<Disapproval> disapprovals = new ArrayList<>();
	
	public Alternative(String description) {
		this.description = description;
		
	}
	
	public void addFeedback(Feedback f) {
		feedback.add(f);
	}
	
	public void addApproval(Approval a) {
		approvals.add(a);
	}
	
	public void removeApproval(Approval a) {
		approvals.remove(a);
	}
	
	public void addDisapproval(Disapproval d) {
		disapprovals.add(d);
	}
	
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
	
	@Override
	public Iterator<Feedback> iterator(){
		return feedback.iterator();
	}

}
