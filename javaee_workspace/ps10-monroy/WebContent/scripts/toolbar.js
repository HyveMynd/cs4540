function showToolBar(){
	if (!isLoggedIn()){
		$("#cart").hide();
		$(".reviews input").hide();
	}
	else{
		$("#cart").show();
		$(".reviews input").show();
	}
	if (!isAdmin()){
		$("#inventory").hide();
	}
	else{
		$("#inventory").show();
	}
}

$(document).ready(function(){
	showToolBar();
});