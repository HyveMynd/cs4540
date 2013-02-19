<?php 
require 'functions.php';
session_start();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style.css" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src=resume.js ></script>
<title>Position Sought</title>
</head>
<body class='pos'>
	<form action="positionSought.php" method="post">
	<h1>Position Sought</h1>
	<h5>Please enter a description of the type of job you are in search for:</h5>
		<textarea name=position rows=5 cols=50 autofocus required><?php sticky('position')?></textarea>
	<input type="button" value=Submit onclick="validate()" />
	</form>
	<p><a href="contactInfo.php" >Contact Info</a></p>
	<p><a href="employmentHistory.php" >Employment History</a></p>
	<p><a href="archive.php" >Archive Resume</a></p>
	<p><a href="viewResume.php" >View Resume</a></p>
</body>
</html>