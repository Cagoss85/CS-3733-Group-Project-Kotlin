/**
 * 
 */


function getChoice(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getChoice_url, true)
	xhr.send(uuid);
	
	console.log("sent");
	
	
	xhr.onloadend = function(){
		if(xhr.readystate == XMLHttpRequest.DONE){
			if(xhr.status == 200){
					console.log ("XHR:" + xhr.responseText);
					processCreateChoiceResponse(xhr.responseText);
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

function processGetChoiceResponse(result){
	console.log("res:" + result);
	
	var js = JSON.parse(result);   //we now have our result
	
	document.getElementById("customURL").value = window.location.href;
	
	var alternatives = result["alternatives"];
	
	var alt1Desc = alternatives[0]["description"];
	document.getElementById("alt1").innerHTML = alt1Desc;
	
	var alt2Desc = alternatives[1]["description"];
	document.getElementById("alt2").innerHTML = alt2Desc;
	
	if(alternatives.length > 2){
		var alt3Desc = alternatives[2]["description"];
		document.getElementById("alt3").innerHTML = alt2Desc;
	}
	
	if(alternatives.length > 3){
		var alt4Desc = alternatives[3]["description"];
		document.getElementById("alt4").innerHTML = alt2Desc;
	}
	
	if(alternatives.length > 4){
		var alt5Desc = alternatives[4]["description"];
		document.getElementById("alt5").innerHTML = alt2Desc;
	}
	
	//Set login interface to be visible:
	document.getElementById("regTitle").style.display='block';
	document.getElementById("regInst1").style.display='block';
	document.getElementById("regInst2").style.display='block';
	document.getElementById("userRegister").style.display='block';
}
