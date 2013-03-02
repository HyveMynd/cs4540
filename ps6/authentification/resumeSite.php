<?php
require $_SERVER['DOCUMENT_ROOT'].'/cs4540/ps6/hidden/dbpassword.php';

class resumeSite {
	var $userName = "";
	var $userPass = "";
	var $userRole;
	
	function CheckLogin(){
		if(!isset($_SESSION)){ session_start(); }
		
		if($userName == ""){
			return false;
		}
		return true;
	}
	
	function Login(){
		$userName = $_POST['loginName'];
		$userPass = $_POST['loginPass'];
		if($this->CheckLoginInDB()){
			session_start();
			$_SESSION['userName'] = $userName;
			return true;
		}
		else
			return false;
	}
	
	function Logout(){
		session_destroy();
		$this->RedirectToURL("../main/contactInfo.php");
	}
	
	function SetPageOrigin(){
		session_start();
		$_SESSION['origin'] = $_SERVER['HTTP_REFERER'];
	}
	
	function RedirectToOrigin(){
		session_start();
		$this->RedirectToURL($_SESSION['origin']);
	}
	
	function RegisterUser(){
		
	}
	
	function CheckRole(){
		return false;
	}
	
	function RedirectToURL($url)
	{
		header("Location: $url");
		exit;
	}
	
	function CheckLoginInDB(){
		$badLogin = false;
		
		if ($badLogin){
			$userName = "";
			$userPass = "";
			return false;
		}
		return true;
	}
}

?>