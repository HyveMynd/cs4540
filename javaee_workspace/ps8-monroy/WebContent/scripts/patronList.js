function loadPatrons(){
	$.ajax({
		url: "GetPatrons",
		success: showPatrons,
		type: "GET"
	});
}

function showPatrons(data){
	var contents = "<tr><th>Name</th><th>Card Num.</th><th>Records</th></tr>";
	for (var i = 0; i < data.patrons.length; i++) {
		var patron = data.patrons[i];
		contents += "<tr><td>" + patron.patronName + "</td>" +
				"<td>" + patron.id + "</td>" +
				"<td><input type='button' name='" + patron.id + "' value='Show' onclick='getRecords(this)'/></td></tr>\n";
	}
	$('#patrontable').html(contents);
}

function getRecords(button){
	var id = button.name;
	saveId(id);
	getPatronBooks(id);
}

function getPatronBooks(id){
	$.ajax({
		url: "PatronRecords",
		success: showRecords,
		data: {id: id},
		type: "GET"
	});
}

function saveId(id){
	$("#id").val(id);
}