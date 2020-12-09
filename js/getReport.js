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
			var description = Choice[i]["description"];
			
			var currentTable = document.getElementById("tableRows").innerHTML;
			
			currentTable = currentTable + "<TR><TD>" + choiceUUID +"</TD> <TD>" + description + "</TD> <TD>" +choiceDateCreated + "</TD> <TD>"+ choiceIsCompleted +"</TD> </TR>";
			
			document.getElementById("tableRows").innerHTML = currentTable;
		
		}
		}
		
}	


function handleDeleteClick(e){
	var form = document.dayInput;
	var data = [];
	
	var numEmpty;
	
	
	if (form.numDays.value == 0){
		numEmpty = true;
		alert("You must enter a timespan!");
	} else{
		numEmpty = false;
	}
	
	if(numEmpty == false){
		
		if(form.numDays.value<0){
		alert("You must enter a valid number!");
		}else{
		
			data ["timeCreatedString"]= numDays.value; 
		
			var js = JSON.stringify(data);
			console.log("JS:" + js);
			var xhr = new XMLHttpRequest();
			xhr.open("POST", deleteStaleRequest_url, true);
	
			xhr.send(js);

			GetReport();
/*
		xhr.onloadend = function (){
			console.log(xhr);
			if(xhr.readyState == XMLHttpRequest.DONE){
				if(xhr.status == 200){
					console.log ("XHR:" + xhr.responseText);
					processGetReportResponse(xhr.responseText);
				} else{
					console.log("actual:" + xhr.responseText);
					var js = JSON.parse(xhr.responseText);
					var err = js["response"];
					alert (err);
				}	
			} else{
				processGetReportResponse("N/A");
			}
		}
		*/
	}
	}
	
}



