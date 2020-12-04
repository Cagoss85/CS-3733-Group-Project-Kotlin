/**
 * 
 */

function approvalControlsVisible(){   //USED TO BOTH ENABLE AND REFRESH STATE (COLOR) OF APPROVAL CONTROLS
	//ALTERNATIVE 1 (ARRAY ID 0)
	document.getElementById("alt1AppButton").style.display='inline-block';
	if(userPresent(0, true)){
		document.getElementById("alt1AppButton").style.backgroundColor='green';
	}
	document.getElementById("alt1AppNum").style.display='inline-block';
	document.getElementById("alt1AppNum").innerHTML = currentChoice["alternatives"][0]["approvals"].length;
	
	//ALTERNATIVE 2 (ARRAY ID 1)
	document.getElementById("alt2AppButton").style.display='inline-block';
	if(userPresent(1, true)){
		document.getElementById("alt1AppButton").style.backgroundColor='green';
	}
	document.getElementById("alt2AppNum").style.display='inline-block';
	document.getElementById("alt2AppNum").innerHTML = currentChoice["alternatives"][1]["approvals"].length;
	
	//ALTERNATIVE 3 (ARRAY ID 2)
	if(numAlternatives >= 3){
		document.getElementById("alt3AppButton").style.display='inline-block';
		if(userPresent(2, true)){
			document.getElementById("alt1AppButton").style.backgroundColor='green';
		}
		document.getElementById("alt3AppNum").style.display='inline-block';
		document.getElementById("alt3AppNum").innerHTML = currentChoice["alternatives"][2]["approvals"].length;	
	} 
	
	//ALTERNATIVE 4 (ARRAY ID 3)
	if(numAlternatives >= 4){
		document.getElementById("alt4AppButton").style.display='inline-block';
		if(userPresent(3, true)){
			document.getElementById("alt1AppButton").style.backgroundColor='green';
		}
		document.getElementById("alt4AppNum").style.display='inline-block';
		document.getElementById("alt4AppNum").innerHTML = currentChoice["alternatives"][3]["approvals"].length;	
	} 
	
	//ALTERNATIVE 5 (ARRAY ID 4)
	if(numAlternatives == 5){
		document.getElementById("alt5AppButton").style.display='inline-block';
		if(userPresent(4, true)){
			document.getElementById("alt1AppButton").style.backgroundColor='green';
		}
		document.getElementById("alt5AppNum").style.display='inline-block';
		document.getElementById("alt5AppNum").innerHTML = currentChoice["alternatives"][4]["approvals"].length;
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

function handleApproval(altNum){
	var data = {}  //variable to assemble our JSON object
	
	data["altID"] = altNum;
	data["choiceUUID"] = uuid;
	data["username"] = username;
	
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", createApproval_url, true);
	
	xhr.send(js);
	
	xhr.onloadend = function() {
		console.log(xhr);
		if(xhr.readyState == XMLHttpRequest.DONE){
			if(xhr.status == 200){
				console.log("XHR: " + xhr.responseText);
				//process response?
				getChoice(true);  //call getChoice to initiate refresh
			} else{
				console.log("actual: " + xhr.responseText);
				alert("Unable to add approval");
				//process error?
			}
		} else{
			alert("Unable to send request");
		}
	}
}