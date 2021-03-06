<?php
if(!isset($_SESSION))
	session_start();
$_SESSION['destination'] = $_SERVER['PHP_SELF'];
require_once("../authentification/auth_config.php");
if(!isset($_SESSION['login']))
{
	$resumeSite->RedirectToURL("login.php");
	exit;
}

include 'header.php';
include 'registrationLinks.php';
require '../helper/functions.php';
?>
<body class='archive'>
	<h1>Archive Resume</h1>
	<form method="post" action="archive/Switch.php">
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
	<input type="hidden" name=login value="<?php echo $_SESSION['login']?>" />
	</form>
	<p><a href="contactInfo.php" >Contact Info</a></p>
	<p><a href="positionSought.php" >Position Sought</a></p>
	<p><a href="employmentHistory.php" >Employment History</a></p>
	<p><a href="viewResume.php" >View Resume</a></p>
</body>
<script src="../scripts/resume.js" type="text/javascript"></script>
</html>