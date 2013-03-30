/*---------Patrons Pages---------*/

function getBooksForPatron(){
	var id = $("#id").val();

	$.ajax({
		url: "PatronRecords",
		success: showRecords,
		data: {id: id},
		type: "GET"
	});
}

function showRecords(data){
	var contents = "<tr><th>Title</th><th>Author</th><th>Checkin</th></tr>";
	if (data.books != null)
		for (var i = 0; i < data.books.length; i++) {
			contents += "<tr><td>" + data.books[i].title + "</td>" +
					"<td>" + data.books[i].author + "</td>" +
					"<td><input type='button' name='" + data.books[i].id + "' value='Checkin' onclick='checkIn(this)'/></td></tr>\n";
		}
	$('#booktable').html(contents);
}

function recordLogin(){
	var login = $("#login").val();
	var register = 0;
	$.ajax({
		url: "LoginPatron",
		data: {login: login, register: register},
		success: recordLoggedIn,
		type: "POST"
	});
}

function recordLoggedIn(data){
	if (!data.status){
		$("#loggedInMessage").text(data.message);
	}
	else{
		setId(data);
		hideLogin(data);
		getBooksForPatron();
	}
}