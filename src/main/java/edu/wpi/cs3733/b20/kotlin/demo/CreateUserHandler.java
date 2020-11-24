package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.UsersDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.AuthenticateUserResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateUserRequest;
import edu.wpi.cs3733.b20.kotlin.demo.model.User;

public class CreateUserHandler implements RequestHandler<CreateUserRequest, AuthenticateUserResponse>{
	LambdaLogger logger;
	
	boolean createUser(String choiceUUID, String username, String password) {
		if(logger != null) {logger.log("in Create User");}
		
		UsersDAO dao = new UsersDAO();
		User user = new User(choiceUUID, username, password);
		try {
			return dao.addUser(user);
		} catch(Exception e) {
			//TODO Auto-gen catch block
			e.printStackTrace();
		}
		return false;
	}
	
	boolean createUser(String choiceUUID, String username) {
		if(logger != null) {logger.log("in Create User");}
			
			UsersDAO dao = new UsersDAO();
			User user = new User(choiceUUID, username);
			try {
				return dao.addUser(user);
			} catch(Exception e) {
				//TODO Auto-gen catch block
				e.printStackTrace();
			}
			return false;
	}
	
	/*
	 * returns true if the username is in the database
	 */
	public boolean userExists(String choiceUUID, String username) {
		if(logger != null) {logger.log("In check user");}
		
		UsersDAO dao = new UsersDAO();
		User user = new User(choiceUUID, username);
		try {
			return dao.lookForUser(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean passwordExists(String choiceUUID, String username) {
		if(logger != null) {logger.log("In check user");}
		
		UsersDAO dao = new UsersDAO();
		User user = new User(choiceUUID, username);
	}
	
	@Override
	public AuthenticateUserResponse handleRequest(CreateUserRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		AuthenticateUserResponse response = null;
		//Conditions for logging in a user
		try {
			//check if the username is in the database
			if(userExists(req.getChoiceUUID(), req.getUsername())) {
				if(req.getPassword() == null) {
					//log in and refresh
				}
				else if(req.getPassword() == passwordExists(req.getPassword())) {
					//log in and refresh
				}
				else {
					//reject user login
				}
			}
			//if the user isnt in the database, check if you can add a user
			else if(spaceAvailable()) {
				if(req.getPassword() == null) {
					//add user without password and refresh
					if(createUser(req.getChoiceUUID(), req.getUsername())) {
						response = new AuthenticateUserResponse(req.getUsername());
					}
					else {
						System.out.println("Failed");
						response = new AuthenticateUserResponse(req.getUsername(), "Choice creation failed");  //specify 400 error
					}
				}
				else if(req.getPassword() != null) {
					//add user with password and refresh
					if(createUser(req.getChoiceUUID(), req.getUsername(), req.getPassword())) {
						response = new AuthenticateUserResponse(req.getUsername());
					}
					else {
						System.out.println("Failed");
						response = new AuthenticateUserResponse(req.getUsername(), "Choice creation failed");  //specify 400 error
					}
				}
			}
			//otherwise reject
			else {
				//reject user creation
			}
		} catch (Exception e) {}
		return response;
	}
}






















