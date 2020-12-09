package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.DeleteStaleRequest;

public class DeleteChoiceHandler implements RequestHandler<DeleteStaleRequest, Object>{
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
	public Object handleRequest(DeleteStaleRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		boolean ret = false;
		try {
			ret = deleteChoices(req.getNumDays());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
