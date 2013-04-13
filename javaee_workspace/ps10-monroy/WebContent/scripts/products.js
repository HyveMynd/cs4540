function getProducts(){
	serverCall("Products", null, showProducts);
}

function showProducts(data){
	var contents = "<tr><th>Brand</th><th>Name</th><th>Quantity</th><th>Add To Cart</th></tr>";
	for (var i = 0; i < data.products.length; i++) {
		contents += "<tr id='" + data.products[i].id + "'><td onclick='getSpecs(this)'>" + data.products[i].brand + "</td>" +
				"<td onclick='getSpecs(this)'>" + data.products[i].name + "</td>" +
				"<td onclick='getSpecs(this)'>" + data.products[i].quantity.toString() + "</td>" +
				"<td><input type='button' name='" + data.products[i].id + "' value='Add' onclick='addToCart(this)'/></td></tr>\n";
	}
	$('.product-table').html(contents);
}

function getSpecs(td){
	var id = td.parentNode.id;
	getReview(td);
	serverCall("Specifications", {id: id}, loadSpecs);
}

function loadSpecs(data){
	$("#specs-desc").html(data.specs);
}