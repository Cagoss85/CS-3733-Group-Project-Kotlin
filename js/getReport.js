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
		document.getElementById("UUID").innerHTML = UUID;
		
		var timeCreated = js["timeCreated"];
		document.getElementById("timeCreated").innerHTML = timeCreated;
		
		var isCompleted = js["isCompleted"];
		document.getElementById("isCompleted").innerHTML = isCompleted;
		
		var Choice = js["Choice"];
		
		//var Choice = ["alt1", "alt2", "isCompleted"];
		
		for (i = 0; i<Choice.length; i++ ){ //fix CHoice.length
			var choiceUUID = Choice[i]["UUID"];
			var choiceTimeCreated = Choice[i]["timeCreated"];
			var choiceIsCompleted = Choice[i]["isCompleted"];
			
			//in element Id should be whatever casey made i think
			document.getElementById("choiceUUID").innerHTML = "Choice UUID:" +choiceUUID;
			document.getElementById("choiceTimeCreated").innerHTML = "Chocie Time Created:" +choiceTimeCreated;
			document.getElementById("choiceIsCompleted").innerHTML = "Choice is Completed:" +choiceIsCompleted;
			//document.getElementById("UUID").innerHTML = UUID;
			//document.getElementById("timeCreated").innerHTML = timeCreated;
			//document.getElementById("isCompleted").innerHTML = isCompleted;
		}
	//establish each variable
	//parse
	//for loop going through an array.
		
		}
		
}
