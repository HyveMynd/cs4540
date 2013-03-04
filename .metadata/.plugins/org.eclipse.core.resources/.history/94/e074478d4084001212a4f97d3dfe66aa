<?php
session_start();
include '../Views/header.php';
include $docRoot.'Views/registrationLinks.php';
require $docRoot.'helper/storeArray.php';
?>	
<link rel="stylesheet" href="../style/style.css" type="text/css" />
</head>
<body class='employ'>
	<form action="employmentHistory.php" method="post">
	<h1>Employment History</h1>
	<input type="button" value="Add Previous Job" onclick="addField()"/>
	<input type="button" value=Submit onclick="validate()"  />
	</form>
	<p><a href="contactInfo.php" >Contact Info</a></p>
	<p><a href="positionSought.php" >Position Sought</a></p>
	<?php include 'goToArchive.php'?>
	<p><a href="viewResume.php" >View Resume</a></p>
</body>
<?php
require $docRoot.'helper/encodeArray.php';
?>
<script type="text/javascript" src="../scripts/resume.js" ></script>
</html>
