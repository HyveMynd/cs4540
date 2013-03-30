/*-------------Checkin--------*/
function checkIn (button){
	var id = $("#id").val();
	var bookId = button.name;
	$.ajax({
		url: "CheckinBooks",
		data: {id: id, bookId: bookId},
		success: getBooksForPatron,
		type: "POST"
	});
}