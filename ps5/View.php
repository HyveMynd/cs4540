<?php
require 'db.php';

$result = viewResume($_REQUEST['name']);
if ($result){
	echo "<script type='text/javascript' >
			window.open('resume.php?name=' + '".$_REQUEST['name']."');
			</script>";
}
else{
	echo "<script type=text/javascript >alert('Operation Failed. Resume does not exist.')</script>";
}

echo "<meta http-equiv='REFRESH' content='0;url=archive.php'>"
?>