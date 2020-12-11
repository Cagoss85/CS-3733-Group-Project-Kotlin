/**
 * 
 */
//GLOBAL NUM ALTERNATIVES
var numAlternatives;

//GLOBAL CURRENT CHOICE PULL
var currentChoice;

function getChoice(registered){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", getChoice_url, true)
	
	var data = {};
	data["uuid"] = uuid
	
	var js = JSON.stringify(data);
	
	console.log("js: " + js);
	
	xhr.send(js);
	
	document.getElementById("loading").style.visibility='visible';
	
	console.log("sent");
	
	
	xhr.onloadend = function(){
		document.getElementById("loading").style.visibility='hidden';
		console.log(xhr);
		if(xhr.readyState == XMLHttpRequest.DONE){
			if(xhr.status == 200){
					console.log ("XHR:" + xhr.responseText);
					processGetChoiceResponse(xhr.responseText, registered);
				} else{
					console.log("actual:" + xhr.responseText);
					var js = JSON.parse(xhr.responseText);
					var err = js["error"];
					alert (err);
				}
		} else{
			console.log("res:" + xhr.responseText);
			
			document.getElementById("customURL").value = window.location.href;  //TODO: COMMENT THIS OUT LATER
			
			document.getElementById("alt1").innerHTML = "Alternatives not found."
			
			
		}
	}
}

//STORAGE LOCATION FOR ALTERNATIVES' FEEDBACK
var feedbackArr = [];

function processGetChoiceResponse(result, registered){
	console.log("res:" + result);
	
	currentChoice = JSON.parse(result);   //we now have our result
	
	if(currentChoice["httpStatus"] == "400" && currentChoice["isClosed"] == false){
		var err = currentChoice["error"];
		alert (err);
	} else{
		document.getElementById("customURL").value = window.location.href;
	
		var choiceDescription = currentChoice["description"];
	
		document.getElementById("choiceDescription").innerHTML = choiceDescription;
	
		var alternatives = currentChoice["alternatives"];
	
		var alt1Desc = alternatives[0]["description"];
		document.getElementById("alt1").innerHTML = "Alternative 1: "+ alt1Desc;
		feedbackArr[0] = alternatives[0]["feedback"];
	
		var alt2Desc = alternatives[1]["description"];
		document.getElementById("alt2").innerHTML = "Alternative 2: "+ alt2Desc;
		feedbackArr[1] = alternatives[1]["feedback"];
		numAlternatives = 2;
		
		if(alternatives.length > 2){
			var alt3Desc = alternatives[2]["description"];
			document.getElementById("alt3").innerHTML = "Alternative 3: " +alt3Desc;
			feedbackArr[2] = alternatives[2]["feedback"];
			numAlternatives = 3;
		}
		
		if(alternatives.length > 3){
			var alt4Desc = alternatives[3]["description"];
			document.getElementById("alt4").innerHTML = "Alternative 4: "+ alt4Desc;
			feedbackArr[3] = alternatives[3]["feedback"];
			numAlternatives = 4;
		}
		
		if(alternatives.length > 4){
			var alt5Desc = alternatives[4]["description"];
			document.getElementById("alt5").innerHTML = "Alternative 5: "+ alt5Desc;
			feedbackArr[4] = alternatives[4]["feedback"];
			numAlternatives = 5;
		}
		
		
		if(registered){
			approvalControlsVisible();
			disapprovalControlsVisible();
			document.getElementById("feedbackInput").value='';
			if(currentAltID>-1){
				showFeedback(currentAltID+1);   //to refresh feedback list if feedback is currently open
			}
			chooseControlsVisible();
		}
		
		if(!registered){	
			//Set login interface to be visible:
			document.getElementById("regTitle").style.display='inline';
			document.getElementById("regInst1").style.display='inline';
			document.getElementById("regInst2").style.display='inline';
			document.getElementById("userRegister").style.display='inline';
		}
		
		if(currentChoice["isClosed"]){
			var err = currentChoice["error"];
			alert (err);
			document.getElementById("alt1AppButton").disabled = true;
			document.getElementById("alt2AppButton").disabled = true;
			document.getElementById("alt3AppButton").disabled = true;
			document.getElementById("alt4AppButton").disabled = true;
			document.getElementById("alt5AppButton").disabled = true;
			
			document.getElementById("alt1DisButton").disabled = true;
			document.getElementById("alt2DisButton").disabled = true;
			document.getElementById("alt3DisButton").disabled = true;
			document.getElementById("alt4DisButton").disabled = true;
			document.getElementById("alt5DisButton").disabled = true;
			
			document.getElementById("alt1CompleteButton").disabled = true;
			document.getElementById("alt2CompleteButton").disabled = true;
			document.getElementById("alt3CompleteButton").disabled = true;
			document.getElementById("alt4CompleteButton").disabled = true;
			document.getElementById("alt5CompleteButton").disabled = true;
			
			document.getElementById("submitFeedbackButton").disabled = true;
			
			document.getElementById("finalAlternativeLabel").innerHTML = alternatives[currentChoice["finalAlternative"]]["description"] + " was chosen as the final alternative.";
			document.getElementById("finalAlternativeLabel").style.display="block";
		}
	}	
}



