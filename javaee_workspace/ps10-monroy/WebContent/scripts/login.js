function login(){
	var login = $("#login").val();
	var password = $("#password").val();
	
	serverCall("Login", {login: login, password: password}, loginSuccess);
}

function loginSuccess(data){
	//save user id in a cookie
	$.cookie("userId", data.id);
	if (data.id > 0){
		$(".login").hide();
	}
	$("#message").val(data.message);
}

