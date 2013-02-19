<?php
session_start();
require 'db.php';

$result = loadResume($_REQUEST['name']);
if($result){
	echo "<script type=text/javascript >alert('Operation Sucessful')</script>";
	$_SESSION['resumeName'] = $_REQUEST['name'];
}
else
	echo "<script type=text/javascript >alert('Operation Failed. Resume does not exist.')</script>";
require 'archive.php';
?>