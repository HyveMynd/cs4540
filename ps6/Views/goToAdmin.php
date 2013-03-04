<?php 
if(!isset($_SESSION))
	session_start();

if($_SESSION['role'] == "admin")
	echo "<p><a href='admin.php' >Admin Page</a></p>";
?>
