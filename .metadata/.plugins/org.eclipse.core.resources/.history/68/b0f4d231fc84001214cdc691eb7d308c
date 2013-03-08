<?php
session_start();
include '../Views/header.php';
include $docRoot.'Views/registrationLinks.php';
require $docRoot.'helper/functions.php';
?>
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
	<?php include 'goToArchive.php'?>
	<p><a href="viewResume.php" >View Resume</a></p>
</body>
<script type="text/javascript" src="../scripts/resume.js" ></script>
</html>