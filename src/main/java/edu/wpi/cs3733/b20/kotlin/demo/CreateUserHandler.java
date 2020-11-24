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
	public boolean userExists(String choiceUUID, String username) throws Exception {
		if(logger != null) {logger.log("In check userExists");}
		
		UsersDAO dao = new UsersDAO();
		User user = new User(choiceUUID, username);
		try {
			if(user.getUsername() == dao.getUser(user).getUsername()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getPassword(String choiceUUID, String username, String password) throws Exception{
		if(logger != null) {logger.log("In get password");}
		
		UsersDAO dao = new UsersDAO();
		User user = new User(choiceUUID, username);
		try {
			String pw = dao.getUser(user).getPassword();
			return pw;
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to get password: " + e.getMessage());
		}
	}
	
	private boolean spaceAvailable(String choiceUUID) throws Exception{
		if(logger != null) {logger.log("In space available");}
		
		UsersDAO dao = new UsersDAO();
		User user = new User(choiceUUID);
		try {
			dao.isThereSpaceFor(user);
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to get space available: " + e.getMessage());
		}
		return false;
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
					response = new AuthenticateUserResponse(req.getUsername());
				}
				else if(req.getPassword() == getPassword(req.getChoiceUUID(), req.getUsername(), req.getPassword())) {
					response = new AuthenticateUserResponse(req.getUsername());
				}
				else {
					response = new AuthenticateUserResponse(req.getUsername(), "User already exists but the password doesn't match.");  //specify 400 error
				}
			}
			//if the user isnt in the database, check if you can add a user
			else if(spaceAvailable(req.getChoiceUUID())) {
				if(req.getPassword() == null) {
					//add user without password and refresh
					if(createUser(req.getChoiceUUID(), req.getUsername())) {
						response = new AuthenticateUserResponse(req.getUsername());
					}
					else {
						System.out.println("Failed");
						response = new AuthenticateUserResponse(req.getUsername(), "User Creation Failed");  //specify 400 error
					}
				}
				else if(req.getPassword() != null) {
					//add user with password and refresh
					if(createUser(req.getChoiceUUID(), req.getUsername(), req.getPassword())) {
						response = new AuthenticateUserResponse(req.getUsername());
					}
					else {
						System.out.println("Failed");
						response = new AuthenticateUserResponse(req.getUsername(), "User creation failed");  //specify 400 error
					}
				}
			}
			//otherwise reject
			else {
				response = new AuthenticateUserResponse(req.getUsername(), "This choice has reach capacity.");  //specify 400 error
			}
		} catch (Exception e) {}
		return response;
	}
}






















