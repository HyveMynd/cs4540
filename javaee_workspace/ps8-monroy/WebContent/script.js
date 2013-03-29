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
			disableCheckoutButton(result.books[i].id);
}

function disableCheckoutButton(id){
	$("input:button[name='"+id+"']").attr('disabled', 'disabled');
	$("input:button[name='"+id+"']").val("Checked Out");
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

/*---------Login functions-----------*/

function login(){
	var login = $("#login").val();
	var register = 0;
	$.ajax({
		url: "Patrons",
		data: {login: login, register: register},
		success: loggedIn,
		type: "POST"
	});
}

function register(){
	var login = $("#login").val();
	var register = 1;
	$.ajax({
		url: "Patrons",
		data: {login: login, register: register},
		success: loggedIn,
		type: "POST"
	});
}

function loggedIn (data){
	if (!data.status){
		$("#loggedInMessage").text(data.message);
	}
	else{
		hideLogin(data);
		setId(data);
		getMoreBooks();
	}
}

function hideLogin(data){
	$("#loginArea").hide();
	$("#loggedInMessage").css("color", "black");
	$("#loggedInMessage").text(data.message);
}

function setId(data){
	$("#id").val(data.id);
}

/*-----------Checkout----------*/

function checkOut(button){
	var bookId = button.name;
	var id = $("#id").val();
	$.ajax({
		url: "Checkout",
		data: {bookId: bookId, id: id},
		success: checkedOutBook,
		type: "POST"
	});
}

function checkedOutBook(data){
	if (data.status){
		disableCheckoutButton(data.bookId);
	}
}

/*---------Patrons Pages---------*/

function getBooksForPatron(){
	$.ajax({
		url: "Records",
		success: showRecords,
		type: "GET"
	});
}

function showRecords(data){
	var contents = "<tr><th>Title</th><th>Author</th><th>Checkin</th></tr>";
	for (var i = 0; i < result.books.length; i++) {
		contents += "<tr><td>" + result.books[i].title + "</td>" +
				"<td>" + result.books[i].author + "</td>" +
				"<td><input type='button' name='" + result.books[i].id + "' value='Checkin' onclick='checkIn(this)'/></td></tr>\n";
	}
	$('#booktable').html(contents);
}

