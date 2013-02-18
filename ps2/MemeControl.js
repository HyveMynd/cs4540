function validate(){
	//ensure the form is correct before submitting
	var errMsg = "";
	
	var invalidText = false;
	//loop through the textboxes for invalid input
	$('input[name^="caption"]').each(function (){
		var value = $.trim($(this).val());
		if(value == '' || value.length > 20){
			$(this).css('border', '3px #FF0000 solid');
			invalidText = true;
		}
	});
	if (invalidText)
		errMsg += "\t*A textbox is either empty, or contains too \n\tmany characters.\n\t";
	
	//loop through the radio groups for invalid input
	var invalidRadio = false;
	$("input:radio").each(function(){
		var name = $(this).attr("name");
		if($("input:radio[name="+name+"]:checked").length == 0){
			$(this).parent().css('border', '3px #FF0000 solid');
			invalidRadio = true;
		}
	});
	if(invalidRadio)
		errMsg += "*A radio group is not selected.\n\t";			

	//if there are no error the form is submittd otherwise there is an error alert displayed
	if(errMsg == 0){
		$("form").submit();
	}
	else{
		alert("There is an error in the form:\n"+errMsg);
	}
}