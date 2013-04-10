function setCookie(name, value){
	$.cookie.json = true;
	$.cookie(name, value);
}

function getCookie(name){
	return $.cookie(name);
}

function removeCookie(name){
	$.removeCookie(name);
}