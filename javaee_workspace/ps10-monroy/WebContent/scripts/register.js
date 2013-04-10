function register(){
	var login = $("#login").val();
	var password = $("#password").val();
	
	serverCall("Register", {login: login, password: password}, registerSuccess);
}

function registerSuccess(data){
	
}