package edu.wpi.cs3733.b20.kotlin.demo;

import java.util.ArrayList;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest,CreateChoiceResponse>{
	LambdaLogger logger;
	
	boolean createChoice(String uuid, ArrayList<Alternative> alternatives, int numUsers, String description) {
		if (logger != null) { logger.log("in createChoice"); }
		
		ChoicesDAO dao = new ChoicesDAO();
		Choice choice = new Choice(uuid, alternatives, numUsers, description);
		try {
			return dao.addChoice(choice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override 
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		String choiceUUID = UUID.randomUUID().toString();   //generating UUID for this choice

		CreateChoiceResponse response = null;
		try {
			if(createChoice(choiceUUID, req.alternatives, req.users, req.description)) {
				response = new CreateChoiceResponse(choiceUUID);
			} else {
				response = new CreateChoiceResponse(choiceUUID, "Choice creation failed");  //specify 400 error
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
