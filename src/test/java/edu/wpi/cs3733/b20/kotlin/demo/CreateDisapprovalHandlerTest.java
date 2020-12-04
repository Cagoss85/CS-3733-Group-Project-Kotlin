package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.CreateDisapprovalRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

public class CreateDisapprovalHandlerTest extends LambdaTest {

	@Test
	public void TestShouldBeOk() {
		ArrayList<Alternative> professors = new ArrayList<Alternative>();
    	CreateChoiceRequest req = new CreateChoiceRequest(professors, 3, "Whos better");

		Alternative heineman = new Alternative("Heineman");
		Alternative wong = new Alternative("Wong");
		
		professors.add(heineman);
		professors.add(wong);
		
		CreateChoiceHandler handler = new CreateChoiceHandler();
		
		String UUID = handler.handleRequest(req, createContext("Creating test choice 1")).uniqueID.toString();
		CreateDisapprovalRequest disapprovalReq = new CreateDisapprovalRequest(0,UUID,"kotlin");
		
		CreateDisapprovalHandler disapprovalHandler = new CreateDisapprovalHandler();
		assertTrue((Boolean)disapprovalHandler.handleRequest(disapprovalReq, createContext("Creating test Disapproval 1")));
	}
	
}
