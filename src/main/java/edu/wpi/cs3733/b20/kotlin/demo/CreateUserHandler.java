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
		if(logger != null) {logger.log("Creating a user with a password");}
		
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
		if(logger != null) {logger.log("Creating the user without password");}
			
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
		if(logger != null) {logger.log("Checking if the user exists in the database.");}
		
		UsersDAO dao = new UsersDAO();
		User user = new User(choiceUUID, username);
		try {
			if(dao.checkForUser(user)) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getPassword(String choiceUUID, String username) throws Exception{
		if(logger != null) {logger.log("Seeing if the found user has a password.");}
		
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
		if(logger != null) {logger.log("Seeing if the choice has user spots available.");}
		
		UsersDAO dao = new UsersDAO();
		User user = new User(choiceUUID);
		try {
			return dao.isThereSpaceFor(user);
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to get space available: " + e.getMessage());
		}
	}
	
	@Override
	public AuthenticateUserResponse handleRequest(CreateUserRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		AuthenticateUserResponse response = null;

		try {
			if(userExists(req.getChoiceUUID(), req.getUsername())) {
				if(getPassword(req.getChoiceUUID(), req.getUsername()) != null) {
					if(getPassword(req.getChoiceUUID(), req.getUsername()).equals(req.getPassword()))
						response = new AuthenticateUserResponse(req.getUsername());
					else
						response = new AuthenticateUserResponse(req.getUsername(), "Incorrect Password.");  //specify 400 error
				}
				else {
					if(req.getPassword() != null && !(req.getPassword().equals(""))) 
						response = new AuthenticateUserResponse(req.getUsername(), "This user exists and doesn't have a password.");  //specify 400 error
					else
						response = new AuthenticateUserResponse(req.getUsername());
				}
			}
			else {
				if(spaceAvailable(req.getChoiceUUID())) {
					if(req.getPassword() != null) {
						if(createUser(req.getChoiceUUID(), req.getUsername(), req.getPassword()))
							response = new AuthenticateUserResponse(req.getUsername());
					}
					else if(req.getPassword() == null) {
						if(createUser(req.getChoiceUUID(), req.getUsername()))
							response = new AuthenticateUserResponse(req.getUsername());
					}
				}
				else
					response = new AuthenticateUserResponse(req.getUsername(), "Sorry, this choice has reach capacity.");  //specify 400 error
			}
		} catch (Exception e) {}
		return response;
	}
}