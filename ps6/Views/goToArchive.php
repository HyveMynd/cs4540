<?php 
if(!isset($_SESSION))
	session_start();

if(isset($_SESSION['login']))
	echo "<p><a href='archive.php' >Archive Resume</a></p>";
?>
