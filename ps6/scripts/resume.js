function addField(){
	$('h1').append("<section><table><tr><td>Enter the Start Date:</td><td><input name='startDate[]' type=date value='' required /></td><td><input class='remove' type='button' value='remove' /></td></tr><tr><td>Enter the End Date:</td><td><input name='endDate[]' type=date value='' required /></td></tr></table><table><tr><th>Enter a brief description of your responsibilities:</th></tr><tr><td><textarea name='desc[]' rows=10 cols=50 required></textarea></td></tr></table></section>");
	attachRemove();
};

function initializeFields(n){
	$('h1').append("<section><table><tr><td>Enter the Start Date:</td><td><input name='startDate[]' type=date value='"+startArray[n]+"' required /></td><td><input class='remove' type='button' value='remove' /></td></tr><tr><td>Enter the End Date:</td><td><input name='endDate[]' type=date value='"+endArray[n]+"' required /></td></tr></table><table><tr><th>Enter a brief description of your responsibilities:</th></tr><tr><td><textarea name='desc[]' rows=10 cols=50 required>"+descArray[n]+"</textarea></td></tr></table></section>");
};

function populateFields(count){
	for (var i = 0; i < count; i++)
		initializeFields(i);
}

function addReadonly(n){
	for (var i = 0; i < n; i++)
		$('#employ').append("<table><tr><td>Enter the Start Date:</td><td><input name='startDate[]' type=date value='"+startArray[i]+"' readonly /></td></tr><tr><td>Enter the End Date:</td><td><input name='endDate[]' type=date value='"+endArray[i]+"' readonly /></td></tr></table><table><tr><th>Enter a brief description of your responsibilities:</th></tr><tr><td><textarea name='desc[]' rows=10 cols=50 readonly>"+descArray[i]+"</textarea></td></tr></table>");
}

function highlightInput(input){
	$(input).css('border', '3px #FF0000 solid');
}

function attachRemove(){
	$('.remove').click(function (){
		removeSection($(this));
	});
}

function removeSection(button){
	$(button).closest($('section')).remove();
}

function ChangeRole(userID){
	$("#role").val(userID);
	$('form').submit();
}

function DeleteUser(userID){
	$("#delete").val(userID);
	$('form').submit();
}

function validateRegistration(){
	if($("#password").val().length < 8){
		highlightInput($("#password"));
		alert("Password must be at least 8 characters long.");
	}
	else{
		if ($("#password").val() != $("#retype").val()){
			highlightInput($("#password"));
			highlightInput($("#retype"));
			alert("Passwords must match.");
		}
		else{
			validate();
		}
	}
}

function Sanatize(data){
	return data.text(data.val());
}

function validate(){
	var invalid = false;
	
	//loop through inputs
	$('input').each(function (){
		Sanatize($(this));
		if ($(this).val() == ''){
			highlightInput($(this));
			invalid = true;
		}
	});
	
	//loop through textareas
	$('textarea').each(function (){
		Sanatize($(this));
		if ($(this).val() == ''){
			highlightInput($(this));
			invalid = true;
		}
	});
	if(!invalid)
		$('form').submit();
}