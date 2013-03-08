<?php 
session_start();
include '../Views/header.php';
include $docRoot.'Views/registrationLinks.php';
require $docRoot.'helper/functions.php';
?>
<body class='pos'>
	<form action="positionSought.php" method="post">
	<h1>Position Sought</h1>
	<h5>Please enter a description of the type of job you are in search for:</h5>
		<textarea name=position rows=5 cols=50 autofocus required><?php sticky('position')?></textarea>
	<input type="button" value=Submit onclick="validate()" />
	</form>
	<p><a href="contactInfo.php" >Contact Info</a></p>
	<p><a href="employmentHistory.php" >Employment History</a></p>
	<?php include 'goToArchive.php'?>
	<p><a href="viewResume.php" >View Resume</a></p>
</body>
<script type="text/javascript" src="../scripts/resume.js" ></script>
</html>