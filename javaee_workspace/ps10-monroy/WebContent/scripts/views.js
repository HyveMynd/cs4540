function loggedInView(){
	$(".login").hide();
	showToolBar();
}

function defaultView(){
	showToolBar();
	$(".login").show();
}