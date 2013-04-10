function isLoggedIn(){
	var user = getCookie("user");
	if (user == null)
		return false;
	
	var role = user.role;
	
	return (role == "admin" || role == "user");
}

function isAdmin(){
	var user = getCookie("user");
	if (user == null)
		return false;
	
	var role = user.role;
	
	return role == "admin";
}