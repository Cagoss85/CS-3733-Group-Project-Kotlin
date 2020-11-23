package edu.wpi.cs3733.b20.kotlin.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceResponse;

public class GetChoiceHandler implements RequestHandler<GetChoiceRequest, GetChoiceResponse>{

	@Override
	public GetChoiceResponse handleRequest(GetChoiceRequest input, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

}
