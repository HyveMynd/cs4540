function login(){
	var login = $("#login").val();
	var password = $("#password").val();
	
	serverCall("Login", {login: login, password: password}, loginSuccess);
}

function loginSuccess(data){
	//save user id in a cookie
	setCookie("user", {userId: data.id, role: data.role});
	if (data.id > 0){
		loggedInView();
	}
	$("#message").text(data.message);
}

function logout(){
	removeCookie("user");
	defaultView();
	$("#message").text("");
}

$(document).ready(function(){
	$("#message").click(logout);
});

