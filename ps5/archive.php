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
<script src=resume.js type="text/javascript"></script>
<title>Employment History</title>
</head>
<body class='archive'>
	<h1>Archive Resume</h1>
	<form method="post" action="Switch.php">
	<table>
		<tr>
			<td><input name="resumeName" type="text" value="<?php sticky('resumeName')?>" maxlength="20" required /></td>
			<td><input type="submit" name=submit value="Store" /></td>
			<td><input type="submit" name=submit value="Load" /></td>
			<td><input type="submit" name=submit value="Delete" /></td>
			<td><input type="submit" name=submit value="View" /></td>
		</tr>
	</table>
	</form>
	<p><a href="contactInfo.php" >Contact Info</a></p>
	<p><a href="positionSought.php" >Position Sought</a></p>
	<p><a href="employmentHistory.php" >Employment History</a></p>
	<p><a href="archive.php" >Archive Resume</a></p>
	<p><a href="resume.php" >View Resume</a></p>
</body>