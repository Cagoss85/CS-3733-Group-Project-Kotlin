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
					processGetReportResponse(xhr.responseText);
				} else{
					console.log("actual:" + xhr.responseText);
					var js = JSON.parse(xhr.responseText);
					var err = js["error"];
					alert (err);
				}
		} else{
			console.log("res:" + xhr.responseText);
			
			document.getElementById("customURL").value = window.location.href;  
			
			
			
			
		}
	}
}

function processGetReportResponse(result){
	console.log("res:" + result);
	
	var js = JSON.parse(result);   //we now have our result
	
	if(js["httpStatus"] == "400"){
		var err = js["error"];
		alert (err);
	} else{
	
		//create choiceReport
		var Choice = js["choiceReport"];
		
		//if there are no choices in the array, say no choices found
		if (Choice.length ==0){
			document.getElementById("choiceList").innerHTML = "<li> No Choices Found </li>";
		}
		
		//sort through the array of choices, assign variables, set the lists.
		for (i = 0; i<Choice.length; i++ ){ 
			var choiceUUID = Choice[i]["uuid"];
			var choiceDateCreated = Choice[i]["timeCreatedString"];
			var choiceIsCompleted = Choice[i]["isChosen"];
			
			var currentListChoiceUUID = document.getElementById("choiceUUIDList").innerHTML;
			var currentListDateCreated = document.getElementById("dateCreatedList").innerHTML;
			var currentListIsCompleted = document.getElementById("choiceIsCompletedList").innerHTML;
			
			currentListChoiceUUID = currentListChoiceUUID + "<li>" + choiceUUID +"</li>";
			
			currentListDateCreated = currentListDateCreated + choiceDateCreated;
			
			currentListIsCompleted = currentListIsCompleted +choiceIsCompleted;
			
			document.getElementById("choiceUUIDList").innerHTML = currentListChoiceUUID;
			document.getElementById("dateCreatedList").innerHTML = currentListDateCreated;
			document.getElementById("choiceIsCompletedList").innerHTML = currentListIsCompleted;
			
			var currentList = document.getElementById("choiceList").innerHTML;
			
			currentList = currentList + "<li> choiceUUID: " + choiceUUID + " dateCreated: " + choiceDateCreated + " choiceCompleted: " + choiceIsCompleted + "</li>";
			document.getElementById("choiceList").innerHTML = currentList;
		
		}
		}
		
}
