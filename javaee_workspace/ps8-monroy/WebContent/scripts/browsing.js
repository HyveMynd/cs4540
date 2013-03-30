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
	var contents = "<tr><th>Title</th><th>Author</th><th>Checkout</th></tr>";
	for (var i = 0; i < result.books.length; i++) {
		contents += "<tr><td>" + result.books[i].title + "</td>" +
				"<td>" + result.books[i].author + "</td>" +
				"<td><input type='button' name='" + result.books[i].id + "' value='Checkout' onclick='checkOut(this)'/></td></tr>\n";
	}
	$('#booktable').html(contents);
	handleButtons(result);
	checkStatuses(result);
}

function checkStatuses(result){
	for (var i = 0; i < result.books.length; i++)
		if (result.books[i].checkedOut || $("#id").val() < 0)
			disableButton(result.books[i].id);
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