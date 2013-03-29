$(document).ready(function (){
	//register handlers
	$("#filter").keyup(getMoreBooks);
	$("#loggedin").val("hello");
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
				"<td><input type='button' name='" + result.books[i].id + "' value='Checkout' /></td></tr>\n";
	}
	$('#booktable').html(contents);
	handleButtons(result);
	checkStatuses(result);
}

function checkStatuses(result){
	for (var i = 0; i < result.books.length; i++)
		if (result.books[i].checkedOut)
			disableCheckoutButton(result.books[i].id);
}

function disableCheckoutButton(id){
	$("input:button[name='"+id+"']").attr('disabled', 'disabled');
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
	var login = $("login").val();
	var register = false;
	$.ajax({
		url: "Patrons",
		data: {login: login, register: register},
		callback: loggedIn,
		type: "POST"
	});
}

function register(){
	var login = $("login").val();
	var register = false;
	$.ajax({
		url: "Patrons",
		data: {login: login, register: register},
		callback: loggedIn,
		type: "POST"
	});
}

function loggedIn (data){
	if (!data.status){
		$("#loggedin").val(data.message);
	}
	else{
		$("#login").css("display", "hidden");
		$("#loggedin").val(data.message);
	}
}

