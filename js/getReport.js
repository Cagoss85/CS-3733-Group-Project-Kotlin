/**
 * 
 */

function getReport(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getReport_url, true)
	
	xhr.send();
	
	console.log("sent");
		
	xhr.onloadend = function(){
		console.log(xhr);
		if(xhr.readyState == XMLHttpRequest.DONE){
			if(xhr.status == 200){
					console.log ("XHR:" + xhr.responseText);
					processGetChoiceResponse(xhr.responseText);
				} else{
					console.log("actual:" + xhr.responseText);
					var js = JSON.parse(xhr.responseText);
					var err = js["error"];
					alert (err);
				}
		} else{
			console.log("res:" + xhr.responseText);
			
			document.getElementById("customURL").value = window.location.href;  //TODO: COMMENT THIS OUT LATER
			
			//document.getElementById("alt1").innerHTML = "Report not found."
			
			
		}
	}
}

function processReportResponse(result){
	console.log("res:" + result);
	
	var js = JSON.parse(result);   //we now have our result
	
	if(js["httpStatus"] == "400"){
		var err = js["error"];
		alert (err);
	} else{
		document.getElementById("customURL").value = window.location.href;
		
		var UUID = js["UUID"];
		document.getElementById("UUID").value = UUID;
		
		var dateCreation = js["dateCreated"];
		document.getElementById("timeCreated").value =timeCreated;
		
		var isCompleted = js["isCompleted"];
		document.getElementById("isCompleted").value =isCompleted;
	
	//establish each variable
	//parse
	//for loop going through an array.
		
		}
		
}
