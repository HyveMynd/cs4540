<?php
if(!isset($_SESSION))
{
	//store arrays if the sessions are not set
	if(isset($_REQUEST['startDate'])){
		$_SESSION["startDate"] = $_REQUEST["startDate"];
		$_SESSION["endDate"] = $_REQUEST["endDate"];
		$_SESSION["desc"] = $_REQUEST["desc"];
	}
	
	$start = $_SESSION["startDate"];
	$end = $_SESSION["endDate"];
	$desc = $_SESSION["desc"];
}
?>