package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.FeedbackDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateFeedbackRequest;
import edu.wpi.cs3733.b20.kotlin.demo.model.Feedback;

public class CreateFeedbackHandler implements RequestHandler<CreateFeedbackRequest, Object>{
	LambdaLogger logger;
	
	public boolean createFeedback(String choiceUUID, int altID, String username, String text) {
		if(logger != null) {logger.log("in create feedback");}
		FeedbackDAO dao = new FeedbackDAO();
		Feedback feedback = new Feedback(choiceUUID, altID, username, text);
		try {
			return dao.addFeedback(feedback);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Object handleRequest(CreateFeedbackRequest input, Context context) {
		logger = context.getLogger();
		logger.log(input.toString());
		boolean ret = false;
		try {
			ret = createFeedback(input.getChoiceUUID(), input.getAltID(), input.getUsername(), input.getText());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
