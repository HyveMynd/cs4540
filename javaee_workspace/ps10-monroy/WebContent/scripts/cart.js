var cart = new Array();

function addToCart(button){
	cart.push(button.name);
}

function getCart(){
	serverCall("GetCart", {cart: cart}, loadCart);
}

function loadCart(data){
	var contents = "<h3>Items</h3><table><tr><th>Brand</th><th>Name</th></tr>";
	for (var i = 0; i < data.products.length; i++) {
		contents += "<tr id='" + data.products[i].id + "'><td onclick='getSpecs(this)'>" + data.products[i].brand + "</td>" +
				"<td onclick='getSpecs(this)'>" + data.products[i].name + "</td>" +
				"<td></tr>\n";
	}
	contents += "</table><input type='button' value='Checkout' onclick='checkout()' />";
	$('.wrap-body').html(contents);
}

function checkout(){
	serverCall("Checkout", {cart:cart}, checkedOut);
}

function checkedOut (data){
	alert("Checkout Complete");
	cart = new Array();
	$('.wrap-body').html("<h3>Cart Empty!</h3>");
}