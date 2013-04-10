function login(){
	var login = $("#login").val();
	var password = $("#password").val();
	
	$.ajax({
		type:"POST",
		url:"",
		data:{login: login, password: password}
	});
}