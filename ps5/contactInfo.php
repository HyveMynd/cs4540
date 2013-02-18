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
<title>Contact Info</title>
</head>
<body class='contact'>
	<form action="contactInfo.php" method="post">
	<h1>Contact Information</h1>
		<table>
			<tr>
				<td>
					<p>Enter your Name:</p>
				</td>
				<td>
					<input name=name type="text" value="<?php sticky('name')?>" required />
				</td>
			</tr>
			<tr>
				<td>
					<p>Enter Your Address</p>
				</td>
				<td>
					<input name=address type="text" value="<?php sticky('address')?>" required />
				</td>
			</tr>
			<tr>
				<td>
					<p>Enter Your Phone Number</p>
				</td>
				<td>
					<input name=phone type="tel" value="<?php sticky('phone')?>" required />
				</td>
			</tr>
		</table>
		<input type="button" value=Submit onclick="validate()" />
	</form>
	<p><a href="positionSought.php" >Position Sought</a></p>
	<p><a href="employmentHistory.php" >Employment History</a></p>
	<p><a href="archive.php" >Archive Resume</a></p>
	<p><a href="resume.php" >View Resume</a></p>
</body>
</html>