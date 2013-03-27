function ServerCall(url, callback){
	$.ajax({
		url:url,
		success:callback,
		type:"GET"
	});
}

function loadFirstTenFromLibrary(){
	
}

function loadNextTenFromLibrary(){
	
}

function loadPreviousTenFromLibarary(){
	
}

function load(data){
	alert("GOT HERE!");
}