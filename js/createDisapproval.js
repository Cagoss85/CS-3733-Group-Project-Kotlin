/**
 * 
 */

function disapprovalControlsVisible(){   //USED TO BOTH ENABLE AND REFRESH STATE (COLOR) OF DISAPPROVAL CONTROLS
	//ALTERNATIVE 1 (ARRAY ID 0)
	document.getElementById("alt1DisButton").style.display='inline-block';
	if(userPresent(0, false)){
		document.getElementById("alt1DisButton").style.backgroundColor='red';
	} else{
		document.getElementById("alt1DisButton").style.backgroundColor='';
	}
	document.getElementById("alt1DisNum").style.display='inline-block';
	document.getElementById("alt1DisNum").innerHTML = currentChoice["alternatives"][0]["disapprovals"].length;
	
	//ALTERNATIVE 2 (ARRAY ID 1)
	document.getElementById("alt2DisButton").style.display='inline-block';
	if(userPresent(1, false)){
		document.getElementById("alt2DisButton").style.backgroundColor='red';
	} else{
		document.getElementById("alt2DisButton").style.backgroundColor='';
	}
	document.getElementById("alt2DisNum").style.display='inline-block';
	document.getElementById("alt2DisNum").innerHTML = currentChoice["alternatives"][1]["disapprovals"].length;
	
	//ALTERNATIVE 3 (ARRAY ID 2)
	if(numAlternatives >= 3){
		document.getElementById("alt3DisButton").style.display='inline-block';
		if(userPresent(2, false)){
			document.getElementById("alt3DisButton").style.backgroundColor='red';
		} else{
			document.getElementById("alt3DisButton").style.backgroundColor='';
		}
		document.getElementById("alt3DisNum").style.display='inline-block';
		document.getElementById("alt3DisNum").innerHTML = currentChoice["alternatives"][2]["disapprovals"].length;	
	} 
	
	//ALTERNATIVE 4 (ARRAY ID 3)
	if(numAlternatives >= 4){
		document.getElementById("alt4DisButton").style.display='inline-block';
		if(userPresent(3, false)){
			document.getElementById("alt4DisButton").style.backgroundColor='red';
		} else{
			document.getElementById("alt4DisButton").style.backgroundColor='';
		}
		document.getElementById("alt4DisNum").style.display='inline-block';
		document.getElementById("alt4DisNum").innerHTML = currentChoice["alternatives"][3]["disapprovals"].length;	
	} 
	
	//ALTERNATIVE 5 (ARRAY ID 4)
	if(numAlternatives == 5){
		document.getElementById("alt5DisButton").style.display='inline-block';
		if(userPresent(4, false)){
			document.getElementById("alt5DisButton").style.backgroundColor='red';
		} else{
			document.getElementById("alt5DisButton").style.backgroundColor='';
		}
		document.getElementById("alt5DisNum").style.display='inline-block';
		document.getElementById("alt5DisNum").innerHTML = currentChoice["alternatives"][4]["disapprovals"].length;
	}
	
}

//function checks if user is present in approval or disapproval list, returns boolean
function userPresent(altNum, type){  //type is TRUE for approval and FALSE for disapproval  //altNum index start at 0
	var present = false;  //to return
	
	var thisAlt = currentChoice["alternatives"][altNum];
	
	var userList;
	if(type){
		userList = thisAlt["approvals"];
	} else{
		userList = thisAlt["disapprovals"];
	}
	
	for(var i = 0; i < userList.length; i++){
		if(userList[i]["username"] == username){
			present = true;
		}
	}
	
	return present;
}

function handleDisapproval(altNum){
	var data = {}  //variable to assemble our JSON object
	
	data["altID"] = altNum-1;
	data["choiceUUID"] = uuid;
	data["username"] = username;
	
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", createDisapproval_url, true);
	
	xhr.send(js);
	
	document.getElementById("loading").style.visibility='visible';
	
	xhr.onloadend = function() {
		document.getElementById("loading").style.visibility='hidden';
		console.log(xhr);
		if(xhr.readyState == XMLHttpRequest.DONE){
			if(xhr.status == 200){
				console.log("XHR: " + xhr.responseText);
				//process response?
				getChoice(true);  //call getChoice to initiate refresh
			} else{
				console.log("actual: " + xhr.responseText);
				alert("Unable to add disapproval");
				//process error?
			}
		} else{
			alert("Unable to send request");
		}
	}
}