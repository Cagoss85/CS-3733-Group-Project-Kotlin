function processAuthenticateReponse(response){
	//TODO
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
		data["username"] = form.username;
		data["password"] = form.password;
		
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", authenticateUser_url, true);
		
		// send the collected data as JSON
		xhr.send(js);
		
		xhr.onloadend = function (){
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