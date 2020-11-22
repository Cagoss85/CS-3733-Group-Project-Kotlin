function processCreateChoiceReponse(response){
	console.log("result:" + result);
}

function handleCreateClick(e){
	var form = document.createChoiceForm;
	
	var data = {};   //variable to assemble our JSON object
	
	var usersNotANumber = isNaN(form.numUsers.value);
	if(usersNotANumber){
		alert("Number of users must be a valid integer!");
	}
	
	var alt1Empty;
	if(form.alt1.value.length == 0){
		alt1Empty = true;
		alert("You must provide a description for alternative 1!")
	} else{
		alt1Empty = false;
	}
	
	var alt2Empty;
	if(form.alt2.value.length == 0){
		alt2Empty = true;
	} else{
		alt2Empty = false;
		alert("You must provide a description for alternative 2!")
	}
	
	var inputTooLong;
	if(form.description.value.length > 512 || form.alt1.value.length > 512 || form.alt2.value.length > 512 || form.alt3.value.length > 512
	|| form.alt4.value.length > 512 || form.alt5.value.length > 512){
		inputTooLong = true;
		alert("Description fields are limited to 512 characters or less in length");
	} else{
		inputTooLong = false;
	}
	
	if(usersNotANumber==false && alt1Empty==false && alt2Empty==false && inputTooLong==false){   //Proceed with constructing JSON output to send to back end
		
		var altArray;
		var alt1 = {"description":form.alt1.value};
		altArray[1] = alt1;
		var alt2 = {"description":form.alt2.value};
		altArray[2] = alt2;
		
		var alt3; var alt4; var alt5;
		
		if(form.alt3.value.length > 0){
			var alt3 = {"description":form.alt3.value};
			altArray[3] = alt3;
		}
		if(form.alt4.value.length > 0){
			var alt4 = {"description":form.alt4.value};
			altArray[4] = alt4;
		}
		if(form.alt5.value.length > 0){
			var alt5 = {"description":form.alt5.value};
			arrayLength = 5;
			altArray[5] = alt5;
		}
		
		data["alternatives"];
		data["users"] = form.numUsers.value;
		data["description"] = form.numUsers.value;
		
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", create_url, true);

		// send the collected data as JSON
		xhr.send(js);
		
		xhr.onloadend = function (){
			console.log(xhr);
			console.log(xhr.request);
			if(xhr.readyState == XMLHTTPRequest.done){
				if(xhr.status == 200){
					console.log ("XHR:" + xhr.responseText);
					processCreateResponse(xhr.responseText);
				} else{
					console.log("actual:" + xhr.responseText);
					var js = JSON.parse(xhr.responseText);
					var err = js["response"];
					alert (err);
				}
			} else{
				processCreateResponse("N/A");
			}
		}
	}
		
	
}