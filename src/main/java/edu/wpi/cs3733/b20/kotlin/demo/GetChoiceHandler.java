package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;
import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;

public class GetChoiceHandler implements RequestHandler<String,GetChoiceResponse>{
	LambdaLogger logger;
	
	boolean choiceExists(String uuid) throws Exception {
		if (logger != null) {logger.log("in getChoice");
		
		try {
			ChoicesDAO.getChoice(uuid);
			return true;
		}catch(Exception e) {
			throw new Exception("Choice does not exist: " + e.getMessage());
			
		}
		}
		// unlikely that this is reached, but compiler throws a fit if this is not here
		return false;
	}
	
	
	
	@Override
	public GetChoiceResponse handleRequest(String uuid, Context context) {
		logger = context.getLogger();
		logger.log("attempting to get choice: " + uuid);
		// initialize the response 
		GetChoiceResponse response = null;
		try {
			if(choiceExists(uuid)) {
				Choice choice = ChoicesDAO.getChoice(uuid);
				
				response = new GetChoiceResponse(choice.getUuid(), choice.getAlternatives(), choice.getMaxUsers(), choice.getDescription(), choice.finalAlternative);
			}
			else {
				System.out.println("choice not found by method")
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("failed to create response to get choice: " + e.getMessage());
		}
		return response;
	}
}
