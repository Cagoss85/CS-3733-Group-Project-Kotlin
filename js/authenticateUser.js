var username;    //GLOBAL USERNAME VARIABLE

function processAuthenticateResponse(response){
	var responseObj = JSON.parse(response);
	
	if(responseObj == null){
		alert("This choice doesn't exist any more");
	}
	else if(responseObj["statusCode"] == "400"){
		var err = responseObj["error"];
		alert (err);
	}else{
		username = responseObj["username"];   //global username variable now set
		
		//hide login
		document.getElementById("regTitle").style.display='none';
		document.getElementById("regInst1").style.display='none';
		document.getElementById("regInst2").style.display='none';
		document.getElementById("userRegister").style.display='none';
		
		//show other UI elements
		approvalControlsVisible();
		disapprovalControlsVisible();
		feedbackControlsVisible();
		chooseControlsVisible();
		document.getElementById("refreshChoice").style.display='block';
	}
	
	
}

function handleRegisterClick(e){
	var form = document.userRegister;
	
	var data = {};
	
	var usernameEmpty;
	if(form.username.value.length == 0){
		usernameEmpty = true;
		alert("Error: Please enter a username!")
	} else{
		usernameEmpty = false;
	}
	
	if(usernameEmpty == false){
		data["choiceUUID"] = uuid;
		data["username"] = form.username.value;
		data["password"] = form.password.value;
		
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", authenticateUser_url, true);
		
		// send the collected data as JSON
		xhr.send(js);
		
		document.getElementById("loading").style.visibility='visible';
		
		xhr.onloadend = function (){
			document.getElementById("loading").style.visibility='hidden';
			console.log(xhr);
			if(xhr.readyState == XMLHttpRequest.DONE){
				if(xhr.status == 200){
					console.log ("XHR:" + xhr.responseText);
					processAuthenticateResponse(xhr.responseText);
				} else{
					console.log("actual:" + xhr.responseText);
					var js = JSON.parse(xhr.responseText);
					var err = js["error"];
					alert (err);
				}
			} else{
				processAuthenticateResponse("N/A");
			}
		}
	}
}