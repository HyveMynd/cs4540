function getReview(td){
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
	$("#reviews-desc").html(data.review);
}

function addReview(){
	
}