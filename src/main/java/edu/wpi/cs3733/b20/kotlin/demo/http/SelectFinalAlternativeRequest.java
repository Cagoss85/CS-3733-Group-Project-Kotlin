package edu.wpi.cs3733.b20.kotlin.demo.http;



public class SelectFinalAlternativeRequest {
	public String uuid;

	public int altID;
	public SelectFinalAlternativeRequest(String uuid,int altID ) {
		this.uuid = uuid;
		
		this.altID = altID;
	}
	
	public SelectFinalAlternativeRequest() {
		
	}
	
	public String getUUID(){
		return uuid;
	}
	public int getAltID() {
		return altID;
	}
	
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	public void setAlternative(int altID) {
	
		this.altID = altID;
	}
	


}
