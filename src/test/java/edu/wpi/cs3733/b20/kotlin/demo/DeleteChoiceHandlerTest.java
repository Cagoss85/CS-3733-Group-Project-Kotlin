package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.http.DeleteStaleRequest;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

public class DeleteChoiceHandlerTest extends LambdaTest{
	
	@Test
    public void testShouldBeOk() throws InterruptedException {
    	CreateChoiceRequest req1 = new CreateChoiceRequest(new ArrayList<Alternative>(), 3, "deletable test choice 1");
    	CreateChoiceRequest req2 = new CreateChoiceRequest(new ArrayList<Alternative>(), 3, "deletable test choice 2");

		CreateChoiceHandler handler = new CreateChoiceHandler();
		
		CreateChoiceResponse response1 = handler.handleRequest(req1, createContext("testing deletion 1"));
		TimeUnit.SECONDS.sleep(10);
		CreateChoiceResponse response2 = handler.handleRequest(req2, createContext("testing deletion 2"));

		assertEquals(200, response1.statusCode);
		assertEquals(200, response2.statusCode);
		
		DeleteChoiceHandler dHandler = new DeleteChoiceHandler();
		DeleteStaleRequest req = new DeleteStaleRequest(5.78704e-5); //delete all choices older than 5 seconds. (Will affect other junit tests)
		assertTrue((Boolean)dHandler.handleRequest(req, createContext("testing delete stale choices")));
    }
}
