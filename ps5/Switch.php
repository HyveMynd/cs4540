<?php
switch ($_REQUEST['submit']){
	case'Store':
		require 'Store.php';
		$_SESSION['resumeName'] = $_REQUEST['name'];
		break;
	case'Delete':
		require 'Delete.php';
		break;
	case'Load':
		require'Load.php';
		break;
	case'View':
		require'View.php';
		break;
}
?>