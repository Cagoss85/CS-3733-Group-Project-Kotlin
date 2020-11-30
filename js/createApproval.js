/**
 * 
 */

function handleApproval(altNum){
	var data = {}  //variable to assemble our JSON object
	
	data["uniqueID"] = uuid;
	data["alternativeNumber"] = altNum;
	data["username"] = username;
	
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", createApproval_url, true);
	
	xhr.send(js);
	
	xhr.onloadend = function() {
		console.log(xhr);
		if(xhr.readyState == XMLHttpRequest.DONE){
			if(xhr.status == 200){
				console.log("XHR: " + xhr.responseText);
				//process response?
				getChoice();  //call getChoice to initiate refresh
			} else{
				console.log("actual: " + xhr.responseText);
				alert("Unable to add approval");
				//process error?
			}
		} else{
			alert("Unable to send request");
		}
	}
}