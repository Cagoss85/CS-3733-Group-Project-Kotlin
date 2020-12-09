/**
 * 
 */

function submitFeedback(){
	var data = {}
	var form = document.leaveFeedbackForm;
	
	data["choiceUUID"] = uuid;
	data["altID"] = currentAltID;
	data["username"] = username;
	data["text"] = form.feedbackInput.value;
	
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", postFeedback_url, true);
	
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
				alert("Unable to post feedback");
			}
		} else{
			alert("Unable to post feedback");
		}
	}
	
}