package edu.wpi.cs3733.b20.kotlin.demo.http;


// fix me?
public class SelectFinalAlternativeRequest {
	public String choiceUUID;

	public int altID;
	public SelectFinalAlternativeRequest(String choiceUUID,int altID ) {
		this.choiceUUID = choiceUUID;
		
		this.altID = altID;
	}
	
	public SelectFinalAlternativeRequest() {
		
	}
	
	public String getChoiceUUID(){
		return choiceUUID;
	}
	public int getAltID() {
		return altID;
	}
	
	public void setChoiceUUID(String choiceUUID) {
		this.choiceUUID = choiceUUID;
	}
	public void setAlternative(int altID) {
	
		this.altID = altID;
	}
	


}
