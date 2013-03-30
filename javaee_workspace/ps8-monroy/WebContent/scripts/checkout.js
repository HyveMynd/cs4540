/*-----------Checkout----------*/
function checkOut(button){
	var bookId = button.name;
	var id = $("#id").val();
	$.ajax({
		url: "CheckoutBooks",
		data: {bookId: bookId, id: id},
		success: checkedOutBook,
		type: "POST"
	});
}

function checkedOutBook(data){
	if (data.status){
		disableButton(data.bookId);
	}
}