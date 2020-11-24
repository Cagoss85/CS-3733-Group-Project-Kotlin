package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.AuthenticateUserResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateUserRequest;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

public class CreateUserHandlerTest extends LambdaTest{
	
	@Test
	public void testUserInput() {
		CreateChoiceRequest choiceReq = new CreateChoiceRequest(new ArrayList<Alternative>(), 10, "Which team is better");
		choiceReq.getAlternatives().add(new Alternative("190"));
		choiceReq.getAlternatives().add(new Alternative("Bert"));
		choiceReq.getAlternatives().add(new Alternative("Not Caseys Team"));
		
		CreateChoiceHandler choiceHandler = new CreateChoiceHandler();
		String sampleUUID = choiceHandler.handleRequest(choiceReq, createContext("Creating test choice")).uniqueID.toString();
		
		CreateUserHandler userHandler = new CreateUserHandler();
		
		//Add a user with a password
		CreateUserRequest userReq1 = new CreateUserRequest(sampleUUID, "user1", "password");
		AuthenticateUserResponse userResponse1 = userHandler.handleRequest(userReq1, createContext("Authenticating user 1"));
		assertEquals(200, userResponse1.statusCode);
		
		//duplicate user 1 (different password)
		CreateUserRequest userReq1a = new CreateUserRequest(sampleUUID, "user1", "diffPassword");
		AuthenticateUserResponse userResponse1a = userHandler.handleRequest(userReq1a, createContext("Rejecting user 1 (wrong pw)"));
		assertEquals(400, userResponse1a.statusCode);
		
		//duplicate user(no password)
		CreateUserRequest userReq1b = new CreateUserRequest(sampleUUID, "user1");
		AuthenticateUserResponse userResponse1b = userHandler.handleRequest(userReq1b, createContext("Rejecting user 1 (no pw)"));
		assertEquals(400, userResponse1b.statusCode);
		
		//Add a user without a password
		CreateUserRequest userReq2 = new CreateUserRequest(sampleUUID, "user2");
		AuthenticateUserResponse userResponse2 = userHandler.handleRequest(userReq2, createContext("Authenticating user 2"));
		assertEquals(200, userResponse2.statusCode);
		
		//duplicate user (with password)
		CreateUserRequest userReq2a = new CreateUserRequest(sampleUUID, "user2", "imposter");
		AuthenticateUserResponse userResponse2a = userHandler.handleRequest(userReq2a, createContext("Authenticating user 2 (with password)"));
		assertEquals(400, userResponse2a.statusCode);
		
		//NOT WORKING
		System.out.println("ReDoing 1");
		//Re-authenticate user 1
		CreateUserRequest userReq3 = new CreateUserRequest(sampleUUID, "user1", "password");
		AuthenticateUserResponse userResponse3 = userHandler.handleRequest(userReq3, createContext("Authenticating user 1"));
		assertEquals(200, userResponse3.statusCode);
		
		System.out.println("ReDoing 2");
		//Re-authenticate user 2
		CreateUserRequest userReq4 = new CreateUserRequest(sampleUUID, "user2");
		AuthenticateUserResponse userResponse4 = userHandler.handleRequest(userReq4, createContext("Re-Authenticating user 2"));
		assertEquals(200, userResponse4.statusCode);
	}
	
	
	@Test
	public void testTooManyUsers() {
		CreateChoiceRequest choiceReq = new CreateChoiceRequest(new ArrayList<Alternative>(), 2, "Which game is better");
		choiceReq.getAlternatives().add(new Alternative("Monopoly"));
		choiceReq.getAlternatives().add(new Alternative("Blokus"));
		choiceReq.getAlternatives().add(new Alternative("TrainGame"));
		
		CreateChoiceHandler choiceHandler = new CreateChoiceHandler();
		String sampleUUID = choiceHandler.handleRequest(choiceReq, createContext("Creating test choice")).uniqueID.toString();
		
		//Add a user with a password
		CreateUserRequest userReq1 = new CreateUserRequest(sampleUUID, "user1", "password1");
		CreateUserHandler userHandler = new CreateUserHandler();
		AuthenticateUserResponse userResponse1 = userHandler.handleRequest(userReq1, createContext("Authenticating user 1"));
		assertEquals(200, userResponse1.statusCode);
		
		//Add a user without a password
		CreateUserRequest userReq2 = new CreateUserRequest(sampleUUID, "user2");
		AuthenticateUserResponse userResponse2 = userHandler.handleRequest(userReq2, createContext("Authenticating user 2"));
		assertEquals(200, userResponse2.statusCode);
		
		//reject too many users
		CreateUserRequest userReq3 = new CreateUserRequest(sampleUUID, "user3", "password");
		AuthenticateUserResponse userResponse3 = userHandler.handleRequest(userReq3, createContext("Reject-Authenticating user 3"));
		assertEquals(400, userResponse3.statusCode);
		
		//re-authenticate user 2
		CreateUserRequest userReq4 = new CreateUserRequest(sampleUUID, "user2");
		AuthenticateUserResponse userResponse4 = userHandler.handleRequest(userReq4, createContext("Re-Authenticating user 2"));
		assertEquals(200, userResponse4.statusCode);
		
		//assertFalse(3, <GETMAXNUMUSERSFROMDATABASE>);
	}
	
	
	
}
