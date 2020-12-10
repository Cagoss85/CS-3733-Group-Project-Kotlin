package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.SelectFinalAlternativeRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.SelectFinalAlternativeResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

class SelectFinalAlternativeHandlerTest extends LambdaTest{

	@Test
	void testSucessfulSelectFinalAlternative() {
		// create a Test choice
		CreateChoiceRequest choiceReq = new CreateChoiceRequest(new ArrayList<Alternative>(), 5, "testGetChoice");
		choiceReq.getAlternatives().add(new Alternative("t1"));
		choiceReq.getAlternatives().add(new Alternative("t2"));
		choiceReq.getAlternatives().add(new Alternative("t3"));
		CreateChoiceHandler choiceHandler = new CreateChoiceHandler();
		String sampleUUID = choiceHandler.handleRequest(choiceReq, createContext("Creating test choice 1")).uniqueID.toString();
		SelectFinalAlternativeHandler selAltHandler = new SelectFinalAlternativeHandler();
		SelectFinalAlternativeRequest selAltReq = new SelectFinalAlternativeRequest(sampleUUID, new Alternative("t2"), 2 );
		SelectFinalAlternativeResponse response1= selAltHandler.handleRequest(selAltReq, createContext("asking to set alternative 2 as final"));
		
		assertEquals(200, response1.statusCode);
		
		
		
		
		
		
		
		
	}

}
