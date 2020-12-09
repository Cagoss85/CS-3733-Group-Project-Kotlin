/**
 * 
 */

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
	
	data["altID"] = altNum-1;
	data["choiceUUID"] = uuid;
	data["username"] = username;
	
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", createApproval_url, true);
	
	xhr.send(js);
	
	document.getElementById("loading").style.visibility='visible';
	
	xhr.onloadend = function() {
		document.getElementById("loading").style.visibility='hidden';
		console.log(xhr);
		if(xhr.readyState == XMLHttpRequest.DONE){
			if(xhr.status == 200){
				console.log("XHR: " + xhr.responseText);
				getChoice(true);  //call getChoice to initiate refresh
			} else{
				console.log("actual: " + xhr.responseText);
				alert("Unable to add approval");
			}
		} else{
			alert("Unable to send request");
		}
	}
}