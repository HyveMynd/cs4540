function addField(){
	$('h1').append("<section><table><tr><td>Enter the Start Date:</td><td><input name='startDate[]' type=text value='' required /></td><td><input class='remove' type='button' value='remove' /></td></tr><tr><td>Enter the End Date:</td><td><input name='endDate[]' type=text value='' required /></td></tr></table><table><tr><th>Enter a brief description of your responsibilities:</th></tr><tr><td><textarea name='desc[]' rows=10 cols=50 required></textarea></td></tr></table></section>");
	attachRemove();
};

function initializeFields(n){
	$('h1').append("<section><table><tr><td>Enter the Start Date:</td><td><input name='startDate[]' type=text value='"+startArray[n]+"' required /></td><td><input class='remove' type='button' value='remove' /></td></tr><tr><td>Enter the End Date:</td><td><input name='endDate[]' type=text value='"+endArray[n]+"' required /></td></tr></table><table><tr><th>Enter a brief description of your responsibilities:</th></tr><tr><td><textarea name='desc[]' rows=10 cols=50 required>"+descArray[n]+"</textarea></td></tr></table></section>");
};

function populateFields(count){
	for (var i = 0; i < count; i++)
		initializeFields(i);
}

function addReadonly(n){
	for (var i = 0; i < n; i++)
		$('#employ').append("<table><tr><td>Enter the Start Date:</td><td><input name='startDate[]' type=text value='"+startArray[i]+"' readonly /></td></tr><tr><td>Enter the End Date:</td><td><input name='endDate[]' type=text value='"+endArray[i]+"' readonly /></td></tr></table><table><tr><th>Enter a brief description of your responsibilities:</th></tr><tr><td><textarea name='desc[]' rows=10 cols=50 readonly>"+descArray[i]+"</textarea></td></tr></table>");
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

function validate(){
	var invalid = false;
	
	//loop through inputs
	$('input').each(function (){
		if ($(this).val() == ''){
			highlightInput($(this));
			invalid = true;
		}
	});
	
	//loop through textareas
	$('textarea').each(function (){
		if ($(this).val() == ''){
			highlightInput($(this));
			invalid = true;
		}
	});
	if(!invalid)
		$('form').submit();
}