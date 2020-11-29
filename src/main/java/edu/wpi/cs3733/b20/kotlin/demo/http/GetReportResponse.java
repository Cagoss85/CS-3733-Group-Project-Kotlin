package edu.wpi.cs3733.b20.kotlin.demo.http;

import java.util.ArrayList;

import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;

public class GetReportResponse {
	public ArrayList<Choice> choiceReport;
	
	public GetReportResponse(ArrayList<Choice> choiceReport) {
		this.choiceReport = choiceReport;
	}
	
	public GetReportResponse() {
		
	}
	
	public ArrayList<Choice> getReport(){return this.choiceReport;}
	public void setReport(ArrayList<Choice> rep) {this.choiceReport = rep;}

}
