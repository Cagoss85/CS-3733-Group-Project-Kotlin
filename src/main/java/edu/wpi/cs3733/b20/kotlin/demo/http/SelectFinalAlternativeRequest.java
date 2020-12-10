package edu.wpi.cs3733.b20.kotlin.demo.http;

import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;

public class SelectFinalAlternativeRequest {
	public String uuid;
	public Alternative alternative;
	public int altID;
	public SelectFinalAlternativeRequest(String uuid, Alternative alternative, int altID ) {
		this.uuid = uuid;
		this.alternative = alternative;
		this.altID = altID;
	}
	
	public SelectFinalAlternativeRequest() {
		
	}
	
	public String getUuid(){
		return uuid;
	}
	public int getAltID() {
		return altID;
	}
	public Alternative getAlternative() {
		return alternative;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public void setAlternative(Alternative alternative,int altID) {
		this.alternative = alternative;
		this.altID = altID;
	}
	


}
