<?php
//output session variables if they are set
function sticky ($name){
	if (isset($_REQUEST[$name])){
		$_SESSION[$name] = $_REQUEST[$name];
		echo $_SESSION[$name];
	}
	elseif (isset($_SESSION[$name])){
		echo $_SESSION[$name];
	}
}
?>