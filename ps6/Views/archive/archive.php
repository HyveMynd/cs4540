<?php
if(!isset($_SESSION))
	session_start();

require_once("../../authentification/auth_config.php");
if(!isset($_SESSION['userName']))
{
	$resumeSite->RedirectToURL("../login.php");
	exit;
}

include '../header.php';
require $docRoot.'helper/functions.php';
?>
<link rel="stylesheet" href="../../style/style.css" type="text/css" />
</head>
<body class='archive'>
	<h1>Archive Resume</h1>
	<form method="post" action="Switch.php">
	<table>
		<tr>
			<td>Resume Name</td>
			<td><input id="resName" name="name" type="text" value="<?php sticky('resumeName')?>" maxlength="20" required /></td>
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
	<p><a href="viewResume.php" >View Resume</a></p>
</body>
<script src="../../scripts/resume.js" type="text/javascript"></script>
</html>