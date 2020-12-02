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
			
			document.getElementById("customURL").value = window.location.href;  //TODO: COMMENT THIS OUT LATER
			
			//document.getElementById("alt1").innerHTML = "Report not found."
			
			
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
		document.getElementById("customURL").value = window.location.href;
		
		var UUID = js["choiceUUID"];
		document.getElementById("choiceUUID").innerHTML = UUID;
		
		var dateCreated = js["dateCreated"];
		document.getElementById("dateCreated").innerHTML = DateCreated;
		
		var isCompleted = js["isCompleted"];
		document.getElementById("isCompleted").innerHTML = isCompleted;
		
		var Choice = js["Choice"];
		
		
		for (i = 0; i<Choice.length; i++ ){ 
			var choiceUUID = Choice[i]["UUID"];
			var choiceDateCreated = Choice[i]["dateCreated"];
			var choiceIsCompleted = Choice[i]["isCompleted"];
			
			//in element Id should be whatever casey made i think
			document.getElementById("choiceUUID").innerHTML = "Choice UUID:" +choiceUUID;
			document.getElementById("choiceDateCreated").innerHTML = "Chocie Date Created:" +choiceDateCreated;
			document.getElementById("choiceIsCompleted").innerHTML = "Choice is Completed:" +choiceIsCompleted;
			
		}
		}
		
}
