function loggedInView(){
	$(".login").hide();
	showToolBar();
}

function defaultView(){
	showToolBar();
	$(".login").show();
	hideRegister();
}

function hideRegister(){
	$(".register").html("");
	$("#registerButton").val("New User");
	
	// Re-map the event listeners
	$("#registerButton").unbind();
	$("#registerButton").click(showRegistration);
}