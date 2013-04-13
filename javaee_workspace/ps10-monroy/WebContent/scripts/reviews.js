function getReview(td){
	$("#reviews-desc").html("");
	var id = td.parentNode.id;
	serverCall("Reviews", {id: id}, loadReview);
}

function loadReview(data){
	var contents = "<tr><th>Rating</th></tr>";
	for (var i = 0; i < data.reviews.length; i++) {
		contents += "<tr id='"+data.reviews[i].id+"'><td onclick='getReviewDesc(this)'>" + data.reviews[i].rating + "/5</td></tr>\n";
	}
	$('.reviews-table').html(contents);
}

function getReviewDesc(td){
	var id = td.parentNode.id;
	serverCall("ReviewDesc", {id: id}, loadReviewDesc);
}

function loadReviewDesc(data){
	$("#reviews-desc").name = data.id;
	$("#reviews-desc").html(data.review);
}

function addReview(){
	var prodId = selectedProduct;
	var userId = getCookie("user").userId;
	var rating = $("#addReviewRating").val();
	var review = $("#addReviewText").val();
	serverCall("AddReview", {prodId: prodId, userId: userId, rating: rating, review: review}, hideAddReview);
}

$(document).ready(function(){
	$("#addReview").click(showAddReview);
});