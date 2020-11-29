/**
 * 
 */

function getReport(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getReport_url, true)
	
	xhr.send();
	
		console.log("sent");
}