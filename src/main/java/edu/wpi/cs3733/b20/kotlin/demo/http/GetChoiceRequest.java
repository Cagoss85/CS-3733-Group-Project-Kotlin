package edu.wpi.cs3733.b20.kotlin.demo.http;

import java.util.ArrayList;

public class GetChoiceRequest {
	
	public String uuid;
	
	public GetChoiceRequest(String uuid) {
		this.uuid = uuid;
	}
	
	public GetChoiceRequest() {
		
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
