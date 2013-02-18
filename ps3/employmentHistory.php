<?php
session_start();
//store arrays if the sessions are not set
if(!isset($_SESSION['startDate'])){
	$_SESSION["startDate"] = $_POST["startDate"];
	$_SESSION["endDate"] = $_POST["endDate"];
	$_SESSION["desc"] = $_POST["desc"];
}

$start = $_SESSION["startDate"];
$end = $_SESSION["endDate"];
$desc = $_SESSION["desc"];
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
<body class='employ'>
	<form action="employmentHistory.php" method="post">
	<h1>Employment History</h1>
	<input type="button" value="Add Previous Job" onclick="addField()"/>
	<input type="button" value=Submit onclick="validate()"  />
	</form>
	<p><a href="contactInfo.php" >Contact Info</a></p>
	<p><a href="positionSought.php" >Position Sought</a></p>
	<p><a href="resume.php" >Resume</a></p>
</body>
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