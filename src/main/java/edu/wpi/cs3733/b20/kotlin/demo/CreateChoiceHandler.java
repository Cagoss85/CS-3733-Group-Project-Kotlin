package edu.wpi.cs3733.b20.kotlin.demo;

import java.util.ArrayList;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.heineman.demo.db.ConstantsDAO;
import edu.wpi.cs.heineman.demo.model.Constant;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;


public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest,CreateChoiceResponse>{
	LambdaLogger logger;
	
	boolean createChoice(ArrayList<Alternative> alternatives, int numUsers, String description) {
		if (logger != null) { logger.log("in createChoice"); }
		ChoicesDAO dao = new ChoicesDAO();
		
		String choiceUUID = UUID.randomUUID().toString();   //generating UUID for this choice
		
		Choice choice = new Choice(choiceUUID, alternatives, numUsers, description);
		
		return dao.addChoice(choice);
		
	}
	
	
	@Override 
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		CreateChoiceResponse response;
		try {
			if(createChoice(, req.users, req.description)) {
				response = new CreateChoiceResponse();
			} else {
				response = new CreateChoiceResponse();  //specify 400 error
			}
		} catch (Exception e) {
			
		}
		
		return response;
	}
}
