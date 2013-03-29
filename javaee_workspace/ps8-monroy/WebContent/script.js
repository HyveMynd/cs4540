var bookIds;

$(document).ready(function (){
	//register handlers
	$("#filter").keyup(getMoreBooks);
});

function getBooks(){
	var filter = $("#filter").val();
	var order = $("input:radio[name='order']:checked").val();
	var action = 0;
		
	getBooksFromServer(action, filter, order);
}

function getMoreBooks(){
	var filter = $("#filter").val();
	var order = $("input:radio[name='order']:checked").val();
	var action = 2;
	getBooksFromServer(action, filter, order);
}

function getBooksFromServer(action, filter, order){
	$.ajax({
		url:"GetBooks",
		data: {order: order, filter: filter, action: action},
		success:loadBooks,
		type:"GET"
	});
}

function loadNextTenFromLibrary(){
	var filter = $("#filter").val();
	var order = $("input:radio[name='order']:checked").val();
	var action = 1;
	getBooksFromServer(action, filter, order);
}

function loadPreviousTenFromLibrary(){
	var filter = $("#filter").val();
	var order = $("input:radio[name='order']:checked").val();
	var action = -1;
	getBooksFromServer(action, filter, order);
}

function loadBooks(result){
	var contents = "<tr><th>Title</th><th>Author</th></tr>";
	for (var i = 0; i < result.books.length; i++) {
		contents += "<tr><td>" + result.books[i].title + "</td>" +
				"<td>" + result.books[i].author + "</td>" +
				"<td><input type='button' name='" + result.books[i].id + "' value='Checkout' /></td></tr>\n";
	}
	$('#booktable').html(contents);
	handleButtons(result);
	getStatusFromServer(result);
}

function getStatusFromServer(result){
	var ids = new Array();
	for (var i = 0; i < result.books.length; i++) {
		ids[i] = result.books[i].id;
	}
	
	$.ajax({
		url: "GetCheckoutStatus",
		data: {ids: ids},
		dataType: 'json',
		success: loadStatus,
		type: "POST"
	});
}

function loadStatus(result){
	bookIds = new Array();
	for (var i = 0; i < result.status.length; i++){
		bookIds[i] = result.status[i];
	}
}

function handleButtons(result){
	if (result.atBottom) {
		$('#next').attr('disabled', 'disabled');
	}
	else {
		$('#next').removeAttr('disabled');
	}
	if (result.atTop) {
		$('#previous').attr('disabled', 'disabled');
	}
	else {
		$('#previous').removeAttr('disabled');
	}
}