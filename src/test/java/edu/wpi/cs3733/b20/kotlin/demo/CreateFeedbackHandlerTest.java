package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.CreateApprovalRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateFeedbackRequest;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import java.util.concurrent.TimeUnit;

public class CreateFeedbackHandlerTest extends LambdaTest{
	
	@Test
	public void TestShouldBeOk() {
		System.out.println("hello1");
		ArrayList<Alternative> colors = new ArrayList<Alternative>();
		Alternative red = new Alternative("Red");
		Alternative blue = new Alternative("Blue");
		colors.add(red);
		colors.add(blue);
		
		CreateChoiceRequest req = new CreateChoiceRequest(colors, 3, "Whats the best color");
    	CreateChoiceHandler handler = new CreateChoiceHandler();

		String sampleUUID = handler.handleRequest(req, createContext("Feedback test choice")).uniqueID.toString();
		System.out.println(sampleUUID);
		
		CreateFeedbackHandler feedbackHandler = new CreateFeedbackHandler();
		
		CreateFeedbackRequest feedbackReq = new CreateFeedbackRequest(sampleUUID, 0, "feedbackUserRed", "red is the best");
		CreateFeedbackRequest feedbackReq2 = new CreateFeedbackRequest(sampleUUID, 0, "feedbackUserBlue", "no blue is");
		CreateFeedbackRequest feedbackReq3 = new CreateFeedbackRequest(sampleUUID, 1, "feedbackUserBlue", "see blue is better");
		CreateFeedbackRequest feedbackReq4 = new CreateFeedbackRequest(sampleUUID, 1, "feedbackUserRed", "Imagine being that wrong");
		CreateFeedbackRequest feedbackReq5 = new CreateFeedbackRequest(sampleUUID, 1, "feedbackUserBlue", "Ha you wish");
		CreateFeedbackRequest feedbackReq6 = new CreateFeedbackRequest(sampleUUID, 1, "feedbackUserRed", "Wrong");
		CreateFeedbackRequest feedbackReq7 = new CreateFeedbackRequest(sampleUUID, 0, "feedbackUserBlue", "Look im in the worst color");
		CreateFeedbackRequest feedbackReq8 = new CreateFeedbackRequest(sampleUUID, 0, "feedbackUserRed", "Wrong again pal");
		
		assertTrue((Boolean)feedbackHandler.handleRequest(feedbackReq, createContext("Creating test feedback")));
		assertTrue((Boolean)feedbackHandler.handleRequest(feedbackReq2, createContext("Creating test feedback2")));
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue((Boolean)feedbackHandler.handleRequest(feedbackReq3, createContext("Creating test feedback3")));
		assertTrue((Boolean)feedbackHandler.handleRequest(feedbackReq4, createContext("Creating test feedback4")));
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue((Boolean)feedbackHandler.handleRequest(feedbackReq5, createContext("Creating test feedback5")));
		assertTrue((Boolean)feedbackHandler.handleRequest(feedbackReq6, createContext("Creating test feedback6")));
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue((Boolean)feedbackHandler.handleRequest(feedbackReq7, createContext("Creating test feedback7")));
		assertTrue((Boolean)feedbackHandler.handleRequest(feedbackReq8, createContext("Creating test feedback8")));
	}
}
