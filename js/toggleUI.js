/**
 * Contains all Javascript calls for showing and hiding UI elements
 */

var currentAlt;  //GLOBAL VARIABLE FOR CURRENTLY SELECTED ALTERNATIVE FOR FEEDBACK

function approvalControlsVisible(){   //USED TO BOTH ENABLE AND REFRESH STATE (COLOR) OF APPROVAL CONTROLS
	//ALTERNATIVE 1 (ARRAY ID 0)
	document.getElementById("alt1AppButton").style.display='inline-block';
	if(userPresent(0, true)){
		document.getElementById("alt1AppButton").style.backgroundColor='green';
	} else{
		document.getElementById("alt1AppButton").style.backgroundColor='';
	}
	document.getElementById("alt1AppNum").style.display='inline-block';
	document.getElementById("alt1AppNum").innerHTML = currentChoice["alternatives"][0]["approvals"].length;
	
	//ALTERNATIVE 2 (ARRAY ID 1)
	document.getElementById("alt2AppButton").style.display='inline-block';
	if(userPresent(1, true)){
		document.getElementById("alt2AppButton").style.backgroundColor='green';
	} else{
		document.getElementById("alt2AppButton").style.backgroundColor='';
	}
	document.getElementById("alt2AppNum").style.display='inline-block';
	document.getElementById("alt2AppNum").innerHTML = currentChoice["alternatives"][1]["approvals"].length;
	
	//ALTERNATIVE 3 (ARRAY ID 2)
	if(numAlternatives >= 3){
		document.getElementById("alt3AppButton").style.display='inline-block';
		if(userPresent(2, true)){
			document.getElementById("alt3AppButton").style.backgroundColor='green';
		} else{
			document.getElementById("alt3AppButton").style.backgroundColor='';
		}
		document.getElementById("alt3AppNum").style.display='inline-block';
		document.getElementById("alt3AppNum").innerHTML = currentChoice["alternatives"][2]["approvals"].length;	
	} 
	
	//ALTERNATIVE 4 (ARRAY ID 3)
	if(numAlternatives >= 4){
		document.getElementById("alt4AppButton").style.display='inline-block';
		if(userPresent(3, true)){
			document.getElementById("alt4AppButton").style.backgroundColor='green';
		} else{
			document.getElementById("alt4AppButton").style.backgroundColor='';
		}
		document.getElementById("alt4AppNum").style.display='inline-block';
		document.getElementById("alt4AppNum").innerHTML = currentChoice["alternatives"][3]["approvals"].length;	
	} 
	
	//ALTERNATIVE 5 (ARRAY ID 4)
	if(numAlternatives == 5){
		document.getElementById("alt5AppButton").style.display='inline-block';
		if(userPresent(4, true)){
			document.getElementById("alt5AppButton").style.backgroundColor='green';
		} else{
			document.getElementById("alt5AppButton").style.backgroundColor='';
		}
		document.getElementById("alt5AppNum").style.display='inline-block';
		document.getElementById("alt5AppNum").innerHTML = currentChoice["alternatives"][4]["approvals"].length;
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

function feedbackControlsVisible(){
	//ALTERNATIVE 1 (ARRAY ID 0)
	document.getElementById("alt1FeedbackButton").style.display='inline-block';
	
	//ALTERNATIVE 2 (ARRAY ID 1)
	document.getElementById("alt2FeedbackButton").style.display='inline-block';
	
	//ALTERNATIVE 3 (ARRAY ID 2)
	if(numAlternatives >= 3){
		document.getElementById("alt3FeedbackButton").style.display='inline-block';
	} 
	
	//ALTERNATIVE 4 (ARRAY ID 3)
	if(numAlternatives >= 4){
		document.getElementById("alt4FeedbackButton").style.display='inline-block';
	} 
	
	//ALTERNATIVE 5 (ARRAY ID 4)
	if(numAlternatives == 5){
		document.getElementById("alt5FeedbackButton").style.display='inline-block';
	}

}

var feedbackShowingFor;
function toggleFeedback(altNum){
	if(feedbackShowingFor == altNum){
		hideFeedback;
	} else{
		showFeedback(altNum);
		feedbackShowingFor = altNum;
	}
}

function showFeedback(altNum){
	currentAlt = altNum;
	
	var altID = altNum-1;
	var thisAlt = currentChoice["alternatives"][altID];   //altNum-1 because array index starts at 0
	var feedbackList = thisAlt["feedback"];  //TODO: Verify name of feedback in GetChoiceResponse
	
	document.getElementById("feedbackInput").innerHTML = "Leave Feedback for Alternative " + altNum + ":";
	document.getElementById("feedbackInput").style.display="inline-block";
	
	docuemnt.getElementById("submitFeedback").style.display="inline-block";
}

function hideFeedback(){
	
}