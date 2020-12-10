package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.SelectFinalAlternativeRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.SelectFinalAlternativeResponse;

public class SelectFinalAlternativeHandler implements RequestHandler<SelectFinalAlternativeRequest,SelectFinalAlternativeResponse>{
	LambdaLogger logger;
	ChoicesDAO choiceDAO = new ChoicesDAO();
	Boolean SelectFinalAlternative(String uuid,int altID) {
		try {
			return choiceDAO.addFinalAlternative(uuid, altID);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		}


	@Override
	public SelectFinalAlternativeResponse handleRequest(SelectFinalAlternativeRequest input, Context context) {
		try{
			SelectFinalAlternativeResponse response = SelectFinalAlternativeResponse(input.getUUID(), input.getAltID());
		}catch(Exception e){
			
		}
		
	}
	
	
	
}

