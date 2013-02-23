<?php
require 'db.php';

$resumeName = $_REQUEST['name'];
$result = deleteResume($resumeName);

if (!$result)
	echo "<script type=text/javascript >alert('Operation Failed. The resume does not exist.')</script>";
else
	echo "<script type=text/javascript >alert('Operation Sucessful')</script>";

echo "<meta http-equiv='REFRESH' content='0;url=archive.php'>"
?>