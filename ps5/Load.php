<?php
session_start();
require 'db.php';

$_SESSION['resumeName'] = $_REQUEST['name'];
$resumeName = $_REQUEST['name'];
loadResume($resumeName);
echo "<script type=text/javascript >alert('Operation Sucessful')</script>";
require 'archive.php';
?>