package edu.wpi.cs3733.b20.kotlin.demo.http;

public class DeleteStaleRequest {
	public double numDays;
	
	public DeleteStaleRequest(double numDays) {
		this.numDays = numDays;
	}
	
	public DeleteStaleRequest() {}
	
	public double getNumDays() {return numDays;}
}
