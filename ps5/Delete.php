<?php
require 'db.php';

$resumeName = $_REQUEST['name'];
$result = deleteResume($resumeName);

if (!$result)
	echo "<script type=text/javascript >alert('Operation Failed. The resume does not exist.')</script>";
else
	echo "<script type=text/javascript >alert('Operation Sucessful')</script>";

require 'archive.php';
?>