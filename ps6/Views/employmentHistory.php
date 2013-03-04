<?php
session_start();
include '../Views/header.php';
include $docRoot.'Views/registrationLinks.php';
//store arrays if the sessions are not set
if(isset($_REQUEST['startDate'])){
	$_SESSION["startDate"] = $_REQUEST["startDate"];
	$_SESSION["endDate"] = $_REQUEST["endDate"];
	$_SESSION["desc"] = $_REQUEST["desc"];
}
if(isset($_SESSION["startDate"])){
	$start = $_SESSION["startDate"];
	$end = $_SESSION["endDate"];
	$desc = $_SESSION["desc"];
}
?>
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
<script type="text/javascript" src="../scripts/resume.js" ></script>
<script type="text/javascript">
<?php
$startjs = json_encode($start);
$endjs = json_encode($end);
$descjs = json_encode($desc);
echo "var startArray = ". $startjs . ";\n";
echo "var endArray = ".$endjs . ";\n";
echo "var descArray = ".$descjs . ";\n";
?>
$('document').ready( function(){
	populateFields(<?php echo count($_SESSION["startDate"]) ?>);
	attachRemove();
});
</script>
</html>
