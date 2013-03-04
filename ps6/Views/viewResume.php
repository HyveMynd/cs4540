<?php
session_start();
include '../Views/header.php';
?>
<body class='pos'>
	<h1>Your Resume</h1>
	<h3>Contact Info</h3>
	<table>
		<tr>
			<td>
				<p>Enter your Name:</p>
			</td>
			<td>
				<input name=name type="text" value="<?php echo $_SESSION['name']?>" readonly />
			</td>
		</tr>
		<tr>
			<td>
				<p>Enter Your Address</p>
			</td>
			<td>
				<input name=address type="text" value="<?php echo $_SESSION['address']?>" readonly />
			</td>
		</tr>
		<tr>
			<td>
				<p>Enter Your Phone Number</p>
			</td>
			<td>
				<input name=phone type="text" value="<?php echo $_SESSION['phone']?>" readonly />
			</td>
		</tr>
	</table>
	<h3>Position Sought</h3>
	<div>
	<textarea name=position rows=5 cols=50 readonly><?php echo $_SESSION['position']?></textarea>
	</div>
	<h3 id=employ>
	</h3>
	<p><a href="contactInfo.php" >Contact Info</a></p>
	<p><a href="positionSought.php" >Position Sought</a></p>
	<p><a href="employmentHistory.php" >Employment History</a></p>
	<?php include 'goToArchive.php'?>
</body>
<script type="text/javascript">
<?php
//conver the php arrays to js arrays
$startjs = json_encode($_SESSION["startDate"]);
$endjs = json_encode($_SESSION["endDate"]);
$descjs = json_encode($_SESSION["desc"]);
echo "var startArray = ". $startjs . ";\n";
echo "var endArray = ".$endjs . ";\n";
echo "var descArray = ".$descjs . ";\n";
?>
$('document').ready( function(){
	addReadonly(<?php echo count($_SESSION["startDate"]) ?>);
});
</script>
<script type="text/javascript" src="../scripts/resume.js" ></script>
</html>