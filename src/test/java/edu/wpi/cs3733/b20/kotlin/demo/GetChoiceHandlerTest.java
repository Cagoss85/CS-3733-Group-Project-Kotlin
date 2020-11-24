package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetChoiceResponse;

import com.google.gson.Gson;

public class GetChoiceHandlerTest extends LambdaTest{

	@Test
	public void testSuccessfulGetChoice() {
		GetChoiceRequest request = new GetChoiceRequest("f4cd5ec7-0261-4daf-8b5b-dc8438eed3ab");
		
		GetChoiceHandler handler = new GetChoiceHandler();
		
		GetChoiceResponse response = handler.handleRequest(request, createContext("create"));
		
		Assert.assertEquals(200, response.httpStatus);
	}

}
