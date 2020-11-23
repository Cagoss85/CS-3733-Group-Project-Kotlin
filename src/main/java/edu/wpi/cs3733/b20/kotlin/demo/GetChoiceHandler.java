package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceResponse;

public class GetChoiceHandler implements RequestHandler<String,GetChoiceResponse>{
	LambdaLogger logger;
	
	boolean choiceExists(String uuid) {
		if (logger != null) {logger.log("in getChoice");
		
		try {
			
		}
		}
	}
	
	
	
	@Override
	public GetChoiceResponse handleRequest(String uuid, Context context) {
		logger = context.getLogger();
		logger.log(uuid);
		
		GetChoiceResponse response = null;
		try {
			if(choiceExists(String uuid)) {
				response = new GetChoiceResponse(that.uuid, that.alternatives, that.maxUsers, that.description, that.finalAlternative);
			}
			else {
				System.out.println("Failed");
				//How do I output this to a string
			}
		}
		return response;
	}
}
