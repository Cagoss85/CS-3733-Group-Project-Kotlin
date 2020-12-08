package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;
import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;

public class GetChoiceHandler implements RequestHandler<GetChoiceRequest,GetChoiceResponse>{
	LambdaLogger logger;
	
	boolean choiceExists(ChoicesDAO dao, String uuid) throws Exception {
		if (logger != null) {logger.log("in getChoice");}

		try {
			dao.getChoice(uuid);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public GetChoiceResponse handleRequest(GetChoiceRequest req, Context context) {
		
		String uuid = req.getUuid();
		logger = context.getLogger();
		logger.log("attempting to get choice: " + uuid);
		// initialize the response 
		GetChoiceResponse response = null;
		
		ChoicesDAO dao = new ChoicesDAO();
		
		try {
			if(choiceExists(dao, uuid)&&dao.isChoiceOpen(uuid)) {
				Choice choice = dao.getChoice(uuid);
				
				response = new GetChoiceResponse(choice.getUuid(), choice.getAlternatives(), choice.getMaxUsers(), choice.getDescription(), choice.finalAlternative);
			}else if(choiceExists(dao, uuid)&&!dao.isChoiceOpen(uuid)){
				response = new GetChoiceResponse(400, "Warning Choice is closed, changes will not be accepted");
			}
			else {
				response = new GetChoiceResponse(404, "Choice not found");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response = new GetChoiceResponse(400, "Failure when getting choice");
		}
		return response;
	}
}
