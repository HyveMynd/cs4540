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

// Redirects to HTTPS
function redirectToHTTPS()
{
	if(!usingHTTPS())
	{
		$redirect = "https://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
		header("Location:$redirect");
		exit();
	}
}

// Reports if https is in use
function usingHTTPS () {
	return isset($_SERVER['HTTPS']) && ($_SERVER['HTTPS'] != "off");
}
?>