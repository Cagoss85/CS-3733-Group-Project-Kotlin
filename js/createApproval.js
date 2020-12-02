/**
 * 
 */

function approvalControlsVisible(){
	document.getElementById("alt1AppButton").style.display='inline-block';
	document.getElementById("alt1AppNum").style.display='inline-block';
	document.getElementById("alt2AppButton").style.display='inline-block';
	document.getElementById("alt2AppNum").style.display='inline-block';
	if(numAlternatives >= 3){
		document.getElementById("alt3AppButton").style.display='inline-block';
		document.getElementById("alt3AppNum").style.display='inline-block';
	} if(numAlternatives >= 4){
		document.getElementById("alt4AppButton").style.display='inline-block';
		document.getElementById("alt4AppNum").style.display='inline-block';
	} if(numAlternatives == 5){
		document.getElementById("alt5AppButton").style.display='inline-block';
		document.getElementById("alt5AppNum").style.display='inline-block';
	}
	
}

function handleApproval(altNum){
	var data = {}  //variable to assemble our JSON object
	
	data["uniqueID"] = uuid;
	data["alternativeNumber"] = altNum;
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
				getChoice();  //call getChoice to initiate refresh
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