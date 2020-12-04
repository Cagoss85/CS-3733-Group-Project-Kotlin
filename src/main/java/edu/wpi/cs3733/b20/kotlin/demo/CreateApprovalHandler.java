package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ApprovalsDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateApprovalRequest;
import edu.wpi.cs3733.b20.kotlin.demo.model.Approval;


public class CreateApprovalHandler implements RequestHandler<CreateApprovalRequest, Object>{
	LambdaLogger logger;
	

	boolean createApproval(String choiceUUID, int altID, String username) {
		if (logger != null) {logger.log("in create approval");}
		ApprovalsDAO dao = new ApprovalsDAO();
		Approval approval = new Approval(choiceUUID, altID, username);
		if (logger != null) {logger.log("create approval, approval generated");}
		try {
			if (logger != null) {logger.log("in create approval try block");}
			return dao.addApproval(approval);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	@Override
	public Object handleRequest(CreateApprovalRequest input, Context context) {
		logger = context.getLogger();
		logger.log(input.toString());
		boolean ret = false; 
		try {
			 ret = createApproval(input.getChoiceUUID(), input.getAltID(),input.getUsername());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ret;
		
	}
	
}
