<?php
if(!isset($_SESSION))
	session_start();
require 'db.php';

$name = $_SESSION['name'];
$phone =  $_SESSION['phone'];
$addr = $_SESSION['address'];
$position = $_SESSION['position'];
$startdate = $_SESSION['startDate'];
$enddate = $_SESSION['endDate'];
$desc = $_SESSION['desc'];

$resumeName = $_REQUEST['name'];
storeResume($resumeName, $name, $phone, $addr, $position, $startdate, $enddate, $desc);
$_SESSION['resumeName'] = $_REQUEST['name'];
echo "<script type=text/javascript >alert('Operation Sucessful')</script>";
echo "<meta http-equiv='REFRESH' content='0;url=archive.php'>"
?>