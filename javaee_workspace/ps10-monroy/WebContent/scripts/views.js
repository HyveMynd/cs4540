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

function showAddReview(){
	$(".wrap-add").html("<span>Rating:</span><input id='addReviewRating' type='text' value='' />" +
			"<span>Review:</span><input id='addReviewText' type='text' value='' />");
	$("#addReview").val("Add Review");
	$("#addReview").unbind();
	$("#addReview").click(addReview);
}

function hideAddReview(data){
	$(".wrap-add").html("");
	$("#addReview").val("Add");
	$("#addReview").unbind();
	$("#addReview").click(showAddReview);
	alert("Review has been added");
}

function showProductBrowse(){
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
	"</div>");
	getProducts();
}

function showInventoryAdd(){
	$(".wrap-body").html("<span>Brand:</span><input type='text' id='brandText' />" +
			"<span>Name:</span><input type='text' id='nameText' />" +
			"<span>Quantity:</span><input type='text' id='quantityText' />" +
			"<input type='button' value='Add Product' onclick='addProduct()' />");
}