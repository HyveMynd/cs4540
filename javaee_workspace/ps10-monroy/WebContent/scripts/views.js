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

function asdf(){
	$(".wrap-body").html("<div class='products'>"+
		"<table class='product-table'></table>"+
	"</div>"+
	"<div class='specs'>"+
		"<p id='specs-desc'></p>"+
	"</div>"+
	"<div class='reviews'>"+
		"<table id='reviews-table'></table>"+
		"<p id='reviews-desc'></p>"+
		"<input type='button' value='Add'  onclick='AddReview(this)'/>"+
		"<input type='button' value='Edit' onclick='EditReview(this)'/>"+
	"</div>");
}