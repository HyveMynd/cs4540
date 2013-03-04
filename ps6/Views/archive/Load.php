<?php
if(!isset($_SESSION))
	session_start();
require '../../database/db.php';

$result = loadResume($_REQUEST['name']);
if($result){
	echo "<script type=text/javascript >alert('Operation Sucessful')</script>";
	$_SESSION['resumeName'] = $_REQUEST['name'];
}
else
	echo "<script type=text/javascript >alert('Operation Failed. Resume does not exist.')</script>";

echo "<meta http-equiv='REFRESH' content='0;url=../archive.php'>"
?>