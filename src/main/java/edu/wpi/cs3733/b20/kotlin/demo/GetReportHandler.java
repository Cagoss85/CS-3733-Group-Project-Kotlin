package edu.wpi.cs3733.b20.kotlin.demo;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.db.ChoicesDAO;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetReportResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;

public class GetReportHandler implements RequestHandler<Object, GetReportResponse>{
	LambdaLogger logger;
	
	//Searches RDS and Database for all Choices
	public ArrayList<Choice> getChoices() throws Exception{
		logger.log("in getChoices");
		ChoicesDAO dao = new ChoicesDAO();
		
		return dao.getAllChoices();
	}
	
	/*
	 * Function returns an array of all choices currently found in the system. 
	 */
	@Override
	public GetReportResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("Generating a report of all choices.");
		
		GetReportResponse response;
		try {
			ArrayList<Choice> reportList = getChoices();

			response = new GetReportResponse(reportList);
		} catch (Exception e) {
			response = new GetReportResponse(400, e.getMessage());
		}
		return response;
	}
}
