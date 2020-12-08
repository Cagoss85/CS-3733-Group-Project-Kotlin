package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.http.CreateChoiceRequest;
import edu.wpi.cs3733.b20.kotlin.demo.http.GetReportResponse;
import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;

public class GetReportHandlerTest extends LambdaTest{
	
	@Test
	public void testReport() {
		
		CreateChoiceHandler choiceHandler = new CreateChoiceHandler();
		CreateChoiceRequest flavorChoice = new CreateChoiceRequest(new ArrayList<Alternative>(), 5, "Best flavor");
		flavorChoice.getAlternatives().add(new Alternative("vanilla"));
		flavorChoice.getAlternatives().add(new Alternative("chocolate"));
		flavorChoice.getAlternatives().add(new Alternative("strawberry"));
		
		choiceHandler.handleRequest(flavorChoice, createContext("Creating getReport Choice 1"));
		
		CreateChoiceRequest movieChoice = new CreateChoiceRequest(new ArrayList<Alternative>(), 7, "Best Movie");
		movieChoice.getAlternatives().add(new Alternative("vanilla"));
		movieChoice.getAlternatives().add(new Alternative("chocolate"));
		movieChoice.getAlternatives().add(new Alternative("strawberry"));
		
		choiceHandler.handleRequest(movieChoice, createContext("Creating getReport Choice 1"));
		
		GetReportHandler handler = new GetReportHandler();
		GetReportResponse resp = handler.handleRequest(null, createContext("Test Report"));
		/*
		//text for printing confirmation
		System.out.println("There are " + resp.choiceReport.size() + " choices in the database");
		int i = 1;
		for(Choice c: resp.choiceReport) {
			System.out.println("Choice " + i + ": UUID: " + c.getUuid() + ", Description: " + c.getDescription() + ", timeCreated: " + c.getTimeCreatedString() + ", isChosen: " + c.getIsChosen());
			i++;
		}
		*/
		assertEquals(200, resp.statusCode);
	}
}
