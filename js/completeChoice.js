/**
 * 
 */

function completeChoice(altNum){
	var altID = altNum-1;
	
	var data = {};
	
	data["choiceUUID"] = uuid;
	data["altID"] = altID;
	
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", completeChoice_url, true);
	
	xhr.send(js);
	
	document.getElementById("loading").style.visibility='visible';
	
	xhr.onloadend = function() {
		document.getElementById("loading").style.visibility='hidden';
		console.log(xhr);
		if(xhr.readyState == XMLHttpRequest.DONE){
			if(xhr.status == 200){
				console.log("XHR: " + xhr.responseText);
				getChoice(true);  //call getChoice to initiate refresh
			} else{
				console.log("actual: " + xhr.responseText);
				alert("Unable to complete choice");
			}
		} else{
			alert("Unable to send request");
		}
	}
}