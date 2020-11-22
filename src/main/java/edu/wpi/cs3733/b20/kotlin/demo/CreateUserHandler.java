package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.UsersDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.AuthenticateUserResponse;
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
	
	@Override
	public AuthenticateUserResponse handleRequest(CreateUserRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		AuthenticateUserResponse response = null;
		try {
			//has a password
			if(req.getPassword() != null) { 
				if(createUser(req.getChoiceUUID(), req.getUsername(), req.getPassword())) {
					response = new AuthenticateUserResponse(req.getUsername());
				}
			}
			//no password
			else if(req.getPassword() == null) {
				if(createUser(req.getChoiceUUID(), req.getUsername())) {
					response = new AuthenticateUserResponse(req.getUsername());
				}
			}
		} catch (Exception e) {}
		return response;
	}
}






















