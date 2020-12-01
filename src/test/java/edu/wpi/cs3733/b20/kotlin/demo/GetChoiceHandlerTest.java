package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

public class GetChoiceHandlerTest extends LambdaTest{

	@Test
	public void testSuccessfulGetChoice() {
		CreateChoiceRequest choiceReq = new CreateChoiceRequest(new ArrayList<Alternative>(), 5, "testGetChoice");
		choiceReq.getAlternatives().add(new Alternative("t1"));
		choiceReq.getAlternatives().add(new Alternative("t2"));
		choiceReq.getAlternatives().add(new Alternative("t3"));
		CreateChoiceHandler choiceHandler = new CreateChoiceHandler();
		String sampleUUID = choiceHandler.handleRequest(choiceReq, createContext("Creating test choice 1")).uniqueID.toString();
		
		GetChoiceRequest request1 = new GetChoiceRequest(sampleUUID);
		GetChoiceHandler handler = new GetChoiceHandler();
		GetChoiceResponse response1 = handler.handleRequest(request1, createContext("create"));
		
		assertEquals(200, response1.httpStatus);
		
		GetChoiceRequest request2 = new GetChoiceRequest("f4cd5ec7-0261-4daf-8b5b-dc8438eed3ab");
		GetChoiceResponse response2 = handler.handleRequest(request2, createContext("create"));
		
		assertEquals(400, response2.httpStatus);
		
		
	}

}