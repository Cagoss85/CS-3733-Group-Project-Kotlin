
var uuid;   //GLOBAL UUID VARIABLE

function checkParam(){
	const params = new URLSearchParams(window.location.search);
	if(params.has('uuid')){
		uuid = params.get('uuid');
		return true;
	} else{
		return false;
	}
}