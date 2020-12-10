package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.DeleteStaleRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.DeleteStaleResponse;

public class DeleteChoiceHandler implements RequestHandler<DeleteStaleRequest, DeleteStaleResponse>{
	LambdaLogger logger;
	
	public boolean deleteChoices(double numDays) {
		if(logger != null) {logger.log("in delete stale choices");}
		ChoicesDAO dao = new ChoicesDAO();
		try {
			return dao.deleteChoices(numDays);
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
		
	@Override
	public DeleteStaleResponse handleRequest(DeleteStaleRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		DeleteStaleResponse ret = null;
		try {
			boolean b = deleteChoices(req.getNumDays());
			ret = new DeleteStaleResponse();
		} catch(Exception e) {
			e.printStackTrace();
			ret = new DeleteStaleResponse(e.getMessage(), 400);
		}
		return ret;
	}
}
