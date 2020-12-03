package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs3733.b20.kotlin.demo.http.CreateApprovalRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

public class TestApprovalHandler extends LambdaTest{

//	@Test
//	public void testSuccessInput() {
//		CreateApprovalHandler handler = new CreateApprovalHandler();
//		assertTrue(handler.createApproval(5, "john", "randomUUID"));
//	}
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
		CreateApprovalRequest approvalReq = new CreateApprovalRequest(0,UUID,"kotlin");
		
		CreateApprovalHandler approvalHandler = new CreateApprovalHandler();
		assertTrue((Boolean)approvalHandler.handleRequest(approvalReq, createContext("Creating test Approval 1")));
		
		
		
		
		
	}
}
