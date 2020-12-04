package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.DisapprovalsDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateDisapprovalRequest;
import edu.wpi.cs3733.b20.kotlin.demo.model.Disapproval;

public class CreateDisapprovalHandler implements RequestHandler<CreateDisapprovalRequest, Object>{
	LambdaLogger logger;
	

	boolean createDisapproval(String choiceUUID, int altID, String username) {
		if (logger != null) {logger.log("in create disapproval");}
		DisapprovalsDAO dao = new DisapprovalsDAO();
		Disapproval disapproval = new Disapproval(choiceUUID, altID, username);
		if (logger != null) {logger.log("create disapproval, disapproval generated");}
		try {
			if (logger != null) {logger.log("in create approval try block");}
			return dao.addDisapproval(disapproval);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	@Override
	public Object handleRequest(CreateDisapprovalRequest input, Context context) {
		logger = context.getLogger();
		logger.log(input.toString());
		boolean ret = false; 
		try {
			 ret = createDisapproval(input.getChoiceUUID(), input.getAltID(),input.getUsername());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ret;
		
	}
}
	