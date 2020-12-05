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

function processGetChoiceResponse(result, registered){
	console.log("res:" + result);
	
	currentChoice = JSON.parse(result);   //we now have our result
	
	if(currentChoice["httpStatus"] == "400"){
		var err = currentChoice["error"];
		alert (err);
	} else{
		document.getElementById("customURL").value = window.location.href;
	
		var choiceDescription = currentChoice["description"];
	
		document.getElementById("choiceDescription").innerHTML = choiceDescription;
	
		var alternatives = currentChoice["alternatives"];
	
		var alt1Desc = alternatives[0]["description"];
		document.getElementById("alt1").innerHTML = "Alternative 1: "+ alt1Desc;
	
		var alt2Desc = alternatives[1]["description"];
		document.getElementById("alt2").innerHTML = "Alternative 2: "+ alt2Desc;
		numAlternatives = 2;
		
		if(alternatives.length > 2){
			var alt3Desc = alternatives[2]["description"];
			document.getElementById("alt3").innerHTML = "Alternative 3: " +alt3Desc;
			numAlternatives = 3;
		}
		
		if(alternatives.length > 3){
			var alt4Desc = alternatives[3]["description"];
			document.getElementById("alt4").innerHTML = "Alternative 4: "+ alt4Desc;
			numAlternatives = 4;
		}
		
		if(alternatives.length > 4){
			var alt5Desc = alternatives[4]["description"];
			document.getElementById("alt5").innerHTML = "Alternative 5: "+ alt5Desc;
			numAlternatives = 5;
		}
		
		if(registered){
			approvalControlsVisible();
		}
		
		if(!registered){	
			//Set login interface to be visible:
			document.getElementById("regTitle").style.display='inline';
			document.getElementById("regInst1").style.display='inline';
			document.getElementById("regInst2").style.display='inline';
			document.getElementById("userRegister").style.display='inline';
		}
	}	
}

function showApprovalList(altNum){
	var thisAlt = currentChoice["alternatives"][altNum-1];   //altNum-1 because array index starts at 0
	var appUserList = thisAlt["approvals"];
	
	var toHTML = "";
	
	for(var i = 0; i < appUserList.length; i++){
		toHTML = toHTML + "<li>" + appUserList[i]["username"] + "</li>"
	}
	
	document.getElementById("appDisList").innerHTML = toHTML;
	
	document.getElementById("appDisList").style.display='block';
}

function showDisapprovalList(altNum){
	var thisAlt = currentChoice["alternatives"][altNum-1];   //altNum-1 because array index starts at 0
	var disUserList = thisAlt["disapprovals"];
	
	var toHTML = "";
	
	for(var i = 0; i < disUserList.length; i++){
		toHTML = toHTML + "<li>" + disUserList[i]["username"] + "</li>"
	}
	
	document.getElementById("appDisList").innerHTML = toHTML;
	
	document.getElementById("appDisList").style.display='block';
}

function hideProvalList(){
	document.getElementById("appDisList").style.display='none';
}
