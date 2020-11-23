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
		CreateChoiceRequest choiceReq = new CreateChoiceRequest(new ArrayList<Alternative>(), 3, "Which team is better");
		choiceReq.getAlternatives().add(new Alternative("190"));
		choiceReq.getAlternatives().add(new Alternative("Bert"));
		choiceReq.getAlternatives().add(new Alternative("Not Caseys Team"));
		
		CreateChoiceHandler choiceHandler = new CreateChoiceHandler();
		String sampleUUID = choiceHandler.handleRequest(choiceReq, createContext("Creating test choice")).uniqueID.toString();
		
		CreateUserRequest userReq1 = new CreateUserRequest(sampleUUID, "user1", "password1");
		CreateUserHandler userHandler = new CreateUserHandler();
		
		//Add a user with a password
		AuthenticateUserResponse userResponse1 = userHandler.handleRequest(userReq1, createContext("Adding user 1"));
		assertEquals(200, userResponse1.statusCode);
		
		//Add a user without a password
		CreateUserRequest userReq2 = new CreateUserRequest(sampleUUID, "user2");
		AuthenticateUserResponse userResponse2 = userHandler.handleRequest(userReq2, createContext("Adding user 2"));
		assertEquals(200, userResponse2.statusCode);
		
		//duplicate user 1 (different password)
		
		CreateUserRequest userReq3 = new CreateUserRequest(sampleUUID, "user1", "password1");
		AuthenticateUserResponse userResponse3 = userHandler.handleRequest(userReq3, createContext("Adding user 3"));
		assertEquals(400, userResponse3.statusCode);
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
		AuthenticateUserResponse userResponse1 = userHandler.handleRequest(userReq1, createContext("Adding user 1"));
		assertEquals(200, userResponse1.statusCode);
		
		//Add a user without a password
		CreateUserRequest userReq2 = new CreateUserRequest(sampleUUID, "user2");
		AuthenticateUserResponse userResponse2 = userHandler.handleRequest(userReq2, createContext("Adding user 2"));
		assertEquals(200, userResponse2.statusCode);
		
		//duplicate user 1 (different password)
		
		CreateUserRequest userReq3 = new CreateUserRequest(sampleUUID, "user3", " ");
		AuthenticateUserResponse userResponse3 = userHandler.handleRequest(userReq3, createContext("Adding user 3"));
		assertEquals(200, userResponse3.statusCode);
		
		//assertFalse(3, <GETMAXNUMUSERSFROMDATABASE>);
	}
}
