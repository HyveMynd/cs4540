function disableButton(id){
	$("input:button[name='"+id+"']").attr('disabled', 'disabled');
	$("input:button[name='"+id+"']").val("Checked Out");
}

function hideLogin(data){
	$("#loginArea").hide();
	$("#loggedInMessage").css("color", "black");
	$("#loggedInMessage").text(data.message);
}

function setId(data){
	$("#id").val(data.id);
}
