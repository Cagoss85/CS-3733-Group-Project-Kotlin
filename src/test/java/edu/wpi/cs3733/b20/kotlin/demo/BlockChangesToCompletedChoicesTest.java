package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.wpi.cs3733.b20.kotlin.demo.db.ApprovalsDAO;
import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.db.FeedbackDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateApprovalRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateDisapprovalRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateFeedbackRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.SelectFinalAlternativeRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.SelectFinalAlternativeResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import edu.wpi.cs3733.b20.kotlin.demo.model.Approval;
import edu.wpi.cs3733.b20.kotlin.demo.model.Disapproval;
import edu.wpi.cs3733.b20.kotlin.demo.model.Feedback;

class BlockChangesToCompletedChoicesTest extends LambdaTest{

	@Test
	void testSucessfulSelectFinalAlternative() {
		// create a Test choice
		CreateChoiceRequest choiceReq = new CreateChoiceRequest(new ArrayList<Alternative>(), 5, "testImmutableClosedChoices");
		choiceReq.getAlternatives().add(new Alternative("t1"));
		choiceReq.getAlternatives().add(new Alternative("t2"));
		choiceReq.getAlternatives().add(new Alternative("t3"));
		CreateChoiceHandler choiceHandler = new CreateChoiceHandler();
		String sampleUUID = choiceHandler.handleRequest(choiceReq, createContext("Creating test choice 1")).uniqueID.toString();
		System.out.println(sampleUUID);
		SelectFinalAlternativeHandler selAltHandler = new SelectFinalAlternativeHandler();
		SelectFinalAlternativeRequest selAltReq = new SelectFinalAlternativeRequest(sampleUUID, 2);
		SelectFinalAlternativeResponse response1= selAltHandler.handleRequest(selAltReq, createContext("asking to set alternative 2 as final"));
		// now we have a closed choice w/ alternative 2 set 
		CreateApprovalHandler approvalHandler = new CreateApprovalHandler();
		CreateDisapprovalHandler disapprovalHandler = new CreateDisapprovalHandler();
		CreateFeedbackHandler feedbackHandler =  new CreateFeedbackHandler();
		
		
		
		
		CreateApprovalRequest approvalReq = new CreateApprovalRequest(0,sampleUUID,"Ceasar");
		CreateDisapprovalRequest disapprovalReq = new CreateDisapprovalRequest(1,sampleUUID,"Marc Antony");
		CreateFeedbackRequest feedbackReq = new CreateFeedbackRequest(sampleUUID, 2, "tommy", "this sucks, vote #1");
		System.out.println(approvalHandler.handleRequest(approvalReq, createContext("testing Approval refusal")));
		assertFalse((Boolean)approvalHandler.handleRequest(approvalReq, createContext("testing Approval refusal")));
		//assertFalse((Boolean)disapprovalHandler.handleRequest(disapprovalReq, createContext("testing Disapproval refusal")));
		//assertFalse((Boolean)feedbackHandler.handleRequest(feedbackReq, createContext("testing Feedback refusal")));
		
		
		
		
		
		
		
		
		
		
		
	}

}