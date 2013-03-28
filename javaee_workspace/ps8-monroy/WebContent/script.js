function ServerCall(url, callback){
	$.ajax({
		url:url,
		success:callback,
		type:"GET"
	});
}

function getMoreBooks(){
	$.get('GetBooks', {offset: 0, filter: ""}, load);
}

function loadFirstTenFromLibrary(){
	
}

function loadNextTenFromLibrary(){
	
}

function loadPreviousTenFromLibarary(){
	
}

function load(result){
	var contents = "";
	for (var i = 0; i < result.books.length; i++) {
		contents += "<tr><td>" + result.books[i] + "</td></tr>\n";
	}
	$('#booktable').html(contents);
}