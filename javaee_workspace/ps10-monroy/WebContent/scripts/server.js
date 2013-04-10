function serverCall(url, data, callback){
	$.ajax({
		url: url,
		data: data,
		success: callback,
		type:"POST"
	});
}