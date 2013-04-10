function register(){
	var login = $("#login").val();
	var password = $("#password").val();
	var name = $("#name").val();
	
	serverCall("Register", {login: login, password: password, name: name}, loginSuccess);
}

function showRegistration(button){
	$(".register").html("<input type='text' id='name' placeholder='Full Name' />");
	$(button).val("Register");
	
	// Re-map the event listeners
	$(button).unbind();
	$(button).click(register);
}

$(document).ready(function(){
	$("#registerButton").click(function(){
		showRegistration(this);
	});
});