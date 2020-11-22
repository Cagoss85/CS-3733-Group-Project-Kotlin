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
	
	var descEmpty;
	if(form.choiceDesc.value.length == 0){
		descEmpty = true;
		alert("You must provide a choice description!")
	} else{
		descEmpty = false;
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
		alert("You must provide a description for alternative 2!")
	} else{
		alt2Empty = false;
		
	}
	
	var inputTooLong;
	if(form.choiceDesc.value.length > 512 || form.alt1.value.length > 512 || form.alt2.value.length > 512 || form.alt3.value.length > 512
	|| form.alt4.value.length > 512 || form.alt5.value.length > 512){
		inputTooLong = true;
		alert("Description fields are limited to 512 characters or less in length");
	} else{
		inputTooLong = false;
	}
	
	var alt4NoAlt3;
	if(form.alt4.value.length > 0 && form.alt3.value.length == 0){
		alt4NoAlt3 = true;
		alert("Error: You have given a fourth alternative without a third alternative!")
	} else{
		alt4NoAlt3 = false;
	}
	
	var alt5NoAlt3Or4;
	if(form.alt5.value.length > 0 && (form.alt3.value.length == 0 || form.alt4.value.length == 0)){
		alt5NoAlt3Or4 = true;
		alert("Error: You have given a fifth alternative without a third or fourth alternative!")
	} else{
		alt5NoAlt3Or4 = false;
	}
	
	if(usersNotANumber==false && descEmpty==false && alt1Empty==false && alt2Empty==false && inputTooLong==false
	&& alt4NoAlt3 == false && alt5NoAlt3Or4 == false){   //Proceed with constructing JSON output to send to back end
		
		data["alternatives"] = [];
		
		var alt1 = {"description":form.alt1.value};
		data.alternatives[0] = alt1;
		var alt2 = {"description":form.alt2.value};
		data.alternatives[1] = alt2;
		
		var alt3; var alt4; var alt5;
		
		if(form.alt3.value.length > 0){
			var alt3 = {"description":form.alt3.value};
			data.alternatives[2] = alt3;
		}
		if(form.alt4.value.length > 0){
			var alt4 = {"description":form.alt4.value};
			data.alternatives[3] = alt4;
		}
		if(form.alt5.value.length > 0){
			var alt5 = {"description":form.alt5.value};
			data.alternatives[4] = alt5;
		}
		
		
		data["users"] = form.numUsers.value;
		data["description"] = form.choiceDesc.value;
		
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", createChoice_url, true);

		// send the collected data as JSON
		xhr.send(js);
		
		xhr.onloadend = function (){
			console.log(xhr);
			console.log(xhr.request);
			if(xhr.readyState == XMLHttpRequest.done){
				if(xhr.status == 200){
					console.log ("XHR:" + xhr.responseText);
					processCreateChoiceResponse(xhr.responseText);
				} else{
					console.log("actual:" + xhr.responseText);
					var js = JSON.parse(xhr.responseText);
					var err = js["response"];
					alert (err);
				}
			} else{
				processCreateChoiceResponse("N/A");
			}
		}
	}
		
	
}