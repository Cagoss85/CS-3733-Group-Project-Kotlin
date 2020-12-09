package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

import com.google.gson.Gson;

public class CreateChoiceHandlerTest extends LambdaTest{

	void testSuccessInput(String incoming) throws IOException {
		CreateChoiceHandler handler = new CreateChoiceHandler();
    	CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);
       
        CreateChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateChoiceHandler handler = new CreateChoiceHandler();
    	CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);

    	CreateChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }
    
    @Test
    public void testShouldBeOk() {
    	ArrayList<Alternative> professors = new ArrayList<Alternative>();
    	CreateChoiceRequest req = new CreateChoiceRequest(professors, 3, "TEST DATE SELECTION");

		Alternative heineman = new Alternative("Heineman");
		Alternative wong = new Alternative("Wong");
		
		professors.add(heineman);
		professors.add(wong);
		
		CreateChoiceHandler handler = new CreateChoiceHandler();
		
		CreateChoiceResponse response = handler.handleRequest(req, createContext("Handling a choice creation request"));

		assertEquals(200, response.statusCode);
    }
    
    @Test
    public void testShouldBeBad() {
    	ArrayList<Alternative> colleges = new ArrayList<Alternative>();
    	CreateChoiceRequest req = new CreateChoiceRequest(colleges, 9, null);

		Alternative wpi = new Alternative("wpi");
		Alternative rpi = new Alternative("rpi");
		
		colleges.add(wpi);
		colleges.add(rpi);
		
		CreateChoiceHandler handler = new CreateChoiceHandler();
		
		CreateChoiceResponse response = handler.handleRequest(req, createContext("Handling a choice creation request"));

		assertEquals(400, response.statusCode);
    }
    
    
    
    
    
    
    
    
    
    
	
}
