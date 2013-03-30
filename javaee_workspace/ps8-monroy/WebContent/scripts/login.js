/*---------Login functions-----------*/

function login(){
	var login = $("#login").val();
	var register = 0;
	$.ajax({
		url: "LoginPatron",
		data: {login: login, register: register},
		success: loggedIn,
		type: "POST"
	});
}

function register(){
	var login = $("#login").val();
	var register = 1;
	$.ajax({
		url: "LoginPatron",
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