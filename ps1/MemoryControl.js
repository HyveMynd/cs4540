var current = 0;
var numbers = new Array();
var rowsColumns;

function displayNumber(button){
	//show number in black
	$(button).css({color:"#000000"});
	var selected = $(button).val();
	//if the number is sequential, do not fade
	if (selected == current+1){
		current++;
	}
	//else reset the numbers to be invisible
	else{
		window.setTimeout(function () {showbutton();}, 500);
		current=0;
	}
	//check for game completion
	if(current == rowsColumns*rowsColumns){
		gameComplete();
	}
}

function showbutton(){
	//reset all the numbers back to blank
	$(".gameboard input").css({color:"#faaaab"});
}

function displayBoard(num){
	rowsColumns = num;
	for (var i = 0; i < num*num; i++){
		numbers[i] = i+1;
	}

	//add the buttons to the gameboard
	var board = "<table>";
	for (var r = 0; r < num; r++){
		var row = "<tr>";
		for (var c = 0; c < num; c++){
			
			//randomly select a number a remove from array
			var key = Math.floor(Math.random() * numbers.length-1);
			var number = numbers.splice(key, 1);
			
			//assign number to button
			row += "<td><input type=button value="+number+" /></td>";
		}
		row += "</tr>";
		board += row;
	}
	board += "</table>";
	$(".gameboard").append(board);
	
	//hide buttons
	$("#num3").hide();
	$("#num5").hide();
	$("#num10").hide();
	
	//tie gameboard buttons
	$(".gameboard input").click(function () { play(this); });
}

function play(button){
	//get the row and column of the button
	var td = $(button).parent();
	var col = td.parent().children().index(td);
	var row = td.parent().parent().children().index(td.parent()); 
	
	//toggle the number to display
	toggle(row, col);
}

function toggle (r,c){
	var button = $(".gameboard input").get(r*rowsColumns+c);
	displayNumber(button);
}

function gameComplete(){
	window.alert("Congratulations! You have won!");
	$("#Reset").show();
}