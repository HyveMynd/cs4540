<?php
session_start();
require 'db.php';

$resumeName = $_REQUEST['name'];
deleteResume($resumeName);
echo "<script type=text/javascript >alert('Operation Sucessful')</script>";
require 'archive.php';
?>